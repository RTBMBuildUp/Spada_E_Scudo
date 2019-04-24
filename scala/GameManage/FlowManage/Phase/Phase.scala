package GameManage.FlowManage.Phase

import GameManage.FlowManage.Choice.{Chant, Choice, Choices, Defend}
import GameManage.FlowManage.{Action, Scheduler}
import GameManage.ParticipantMap.ParticipantMap

import scala.collection.immutable.Queue

trait Phase {
  def start(scheduler: Scheduler): Scheduler
}

case object MainPhase extends Phase {
  override def start(scheduler: Scheduler): Scheduler = {
    def declearAction(executerName: String): (Choice, ParticipantMap => ParticipantMap) = {
      def action: (Choice, ParticipantMap => ParticipantMap) = {
        val key = readLine()

        Choices.find(key) match {
          case None => action
          case Some(value)
            if value == Chant &&
                    scheduler.participantMap(executerName).spellLst.isEmpty =>
            action
          case Some(value) =>
            value -> value.declareTarget(executerName, scheduler, readLine)
        }
      }

      println(executerName + ": 攻撃するか防御するか呪文を唱えるか。")
      action
    }

    val participantNameLst = scheduler.participantMap.toList.unzip._1
    val actionQueue = scheduler.actionQueue

    def makeActionQueue: Queue[(String, ParticipantMap => ParticipantMap)] = {
      def actionMap(
                           participantNameLst: List[String] = participantNameLst,
                           result: List[(String, (Choice, ParticipantMap => ParticipantMap))] = Nil
                   ): List[(String, (Choice, ParticipantMap => ParticipantMap))] = participantNameLst match {
        case Nil => result
        case name :: tail =>
          actionMap(
            tail,
            if (scheduler.participantMap(name).isAlive) name -> declearAction(name) :: result else result
          )
      }

      val partedActionMap = actionMap().reverse.partition(_._2._1 == Choices.find("Defend").get)
      val dif = partedActionMap._1.map(elem => (elem._1, elem._2._2))
      val atk = partedActionMap._2.map(elem => (elem._1, elem._2._2))

      Queue(dif ::: atk: _*)
    }

    Scheduler(
      scheduler.participantMap,
      CombatPhase,
      makeActionQueue
    ).goAhead()
  }
}

case object CombatPhase extends Phase {
  override def start(scheduler: Scheduler): Scheduler = {
    def execute(
                       actionLst: List[(String, ParticipantMap => ParticipantMap)] = scheduler.actionQueue.toList,
                       participantMap: ParticipantMap = scheduler.participantMap
               ): ParticipantMap = actionLst match {
      case Nil => participantMap
      case (participantName, act) :: tail =>
        execute(
          tail.filter { case (name, _) => participantMap(name).isAlive },
          if (participantMap(participantName).isAlive) act(participantMap) else participantMap
        )
    }

    Scheduler(execute(), EndPhase)
  }
}

case object EndPhase extends Phase {
  override def start(scheduler: Scheduler): Scheduler = {
    val clearedEffectParticipantMap = scheduler.participantMap.map(tuple => (tuple._1, tuple._2.clearEffect))
    val sortedParticipantMap = clearedEffectParticipantMap.toList.sortWith((l, r) => l._2.speed > r._2.speed).toMap

    scheduler.participantMap.toList.unzip._2.map(_.hp).foreach(println)
    Scheduler(sortedParticipantMap, MainPhase)
  }
}
