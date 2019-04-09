package GameManage

import Creature.{Creature, CreatureUtility}
import GameManage.FlowManage.{MainPhase, Scheduler}

object Game {
  def start(participantLst: List[Creature]): Unit = {
    def play(scheduler: Scheduler): Unit = scheduler.goAhead(Commander.apply) match {
      case round if round.participantMap.toList.unzip._2.count(_.isAlive) == 1 =>
      case round => play(round)
    }

    val sortedParticipantLst = participantLst.sortWith((l, r) => l.speed < r.speed)

    play(Scheduler(sortedParticipantLst.foldLeft(Map[String, Creature]())((res, elem) => res + CreatureUtility.creatureToMapElem(elem)), MainPhase))
  }
}
