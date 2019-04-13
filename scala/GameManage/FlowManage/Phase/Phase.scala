package GameManage.FlowManage.Phase

import Creature.Creature
import GameManage.FlowManage.Action.Chant
import GameManage.FlowManage.Choice.{Choice, Choices}
import GameManage.FlowManage.{Action, Scheduler}
import Identifilable.Identifilable

import scala.collection.immutable.Queue

trait Phase {
  def start(scheduler: Scheduler): Scheduler
}

case object MainPhase extends Phase {
  override def start(scheduler: Scheduler): Scheduler = {
    def declearAction(executerName: String): (Identifilable, Map[String, Creature] => Map[String, Creature]) = {
      def action: (Identifilable, Map[String, Creature] => Map[String, Creature]) = {
        val choice = readLine()
        val choiceLst: List[Choice with Identifilable] =
          if (scheduler.participantMap(executerName).spellLst != Nil) Choices.lst else Choices.lst.filter(_ != Chant)

        choiceLst.filter(_.identify == choice) match {
          case Nil => action
          case action :: _ =>
            action -> action.declareTarget(executerName, scheduler.participantMap, readLine)
        }
      }

      println(executerName + ": 攻撃するか防御するか呪文を唱えるか。")
      action
    }

    val participantNameLst = scheduler.participantMap.toList.unzip._1
    val actionQueue = scheduler.actionQueue

    def makeActionQueue(participantNameLst: List[String] = participantNameLst): Queue[Map[String, Creature] => Map[String, Creature]] = {
      val pair = participantNameLst.map(creatureName => declearAction(creatureName)).partition(_._1 == Action.Defend)
      val dif = pair._1.map(_._2)
      val atk = pair._2.map(_._2)

      Queue(dif ::: atk: _*)
    }

    Scheduler(
      scheduler.participantMap,
      CombatPhase,
      makeActionQueue()
    ).goAhead()
  }
}

case object CombatPhase extends Phase {
  override def start(scheduler: Scheduler): Scheduler = {
    def execute(
                       actionLst: List[Map[String, Creature] => Map[String, Creature]] = scheduler.actionQueue.toList,
                       participantMap: Map[String, Creature] = scheduler.participantMap
               ): Map[String, Creature] =
      actionLst match {
        case Nil => participantMap
        case action :: tail => execute(tail, action(participantMap))
      }

    Scheduler(execute(), EndPhase)
  }
}

case object EndPhase extends Phase {
  override def start(scheduler: Scheduler): Scheduler =
    Scheduler(scheduler.participantMap.map(tuple => (tuple._1, tuple._2.clearEffect)), MainPhase)
}
