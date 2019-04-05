package GameManage

import Creature.Creature
import Queue.Queue

trait Phase {
  def start(player: Commander, scheduler: Scheduler): Scheduler
  def end(map: Map[String, Creature]): Scheduler
}

case object MainPhase extends Phase {
  override def start(commander: Commander, scheduler: Scheduler): Scheduler = {
    def declearAction(creature: Creature): (Creature, Action) = {
      def declear(): Action = {
        println(creature + ": 攻撃するか防御するか。")
        readLine() match {
          case message if message == Choices.Attack.identificationString => Choices.Attack
          case message if message == Choices.Defend.identificationString => Choices.Defend
          case _ => declear()
        }
      }

      creature -> declear()
    }

    val participantLst = scheduler.participantMap.toList.unzip._2
    val actionQueue = scheduler.actionQueue

    def makeActionQueue(participantLst: List[Creature] = participantLst,
                        atkLst: List[(Creature, Action)] = Nil,
                        defLst: List[(Creature, Action)] = Nil
                       ): Queue[(Creature, Action)] =
      participantLst match {
        case Nil => Queue((atkLst ::: defLst).reverse)
        case creature :: tail => declearAction(creature) match {
          case atk if atk._2 == Choices.Attack => makeActionQueue(tail, atk :: atkLst, defLst)
          case dif if dif._2 == Choices.Defend => makeActionQueue(tail, atkLst, dif :: defLst)
        }
      }

    Scheduler(
      scheduler.participantMap,
      CombatPhase,
      makeActionQueue()
    ).goAhead(commander)
  }

  override def end(map: Map[String, Creature]): Scheduler = ???
}

case object CombatPhase extends Phase {
  override def start(player: Commander, scheduler: Scheduler): Scheduler = {
    def execute(actionLst: List[(Creature, Action)] = scheduler.actionQueue.toList, participantMap: Map[String, Creature] = scheduler.participantMap): Map[String, Creature] =
      actionLst match {
        case Nil => participantMap
        case (creature, action) :: tail => execute(tail, participantMap ++ player.command(creature, action, participantMap))
    }
    Scheduler(execute().map(tuple => (tuple._1, tuple._2.clearEffect)), EndPhase)
  }

  override def end(map: Map[String, Creature]): Scheduler = ???
}

case object EndPhase extends Phase {
  override def start(player: Commander, scheduler: Scheduler): Scheduler =
    Scheduler(scheduler.participantMap, MainPhase)

  override def end(map: Map[String, Creature]): Scheduler = ???
}
