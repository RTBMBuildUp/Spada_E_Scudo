package GameManage

import Creature.Creature
import Queue.Queue
import Status.{HP, Status}

trait Phase {
  def start(player: Commander, scheduler: Scheduler): Scheduler
  def end(map: Map[String, Creature]): Scheduler
}

case object MainPhase extends Phase {
  override def start(commander: Commander, scheduler: Scheduler): Scheduler = {
    def declearAction(creature: Creature): (Creature, Action) = {
      def declear(): Action = {
        println("you can choose attack or defence")
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

    Scheduler(
      scheduler.participantMap,
      CombatPhase,
      participantLst.foldLeft(actionQueue: Queue[(Creature, Action)])((res, elem) => res.enqueue(declearAction(elem)))
    ).goAhead(commander)
  }

  override def end(map: Map[String, Creature]): Scheduler = ???
}

case object CombatPhase extends Phase {
  override def start(player: Commander, scheduler: Scheduler): Scheduler = {
    def execute(actionLst: List[(Creature, Action)] = scheduler.actionQueue.toList, participantMap: Map[String, Creature] = scheduler.participantMap): Map[String, Creature] =
      actionLst match {
        case Nil => participantMap
        case (creature, action) :: tail => execute(tail, participantMap ++ player.command(creature, action, scheduler.participantMap))
    }
    Scheduler(execute(), EndPhase)
  }

  override def end(map: Map[String, Creature]): Scheduler = ???
}

case object EndPhase extends Phase {
  override def start(player: Commander, scheduler: Scheduler): Scheduler = ???

  override def end(map: Map[String, Creature]): Scheduler = ???
}
