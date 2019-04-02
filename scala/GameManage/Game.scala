package GameManage

import Creature.{Creature, Monster}
import Queue.Queue
import Status.{Figure, HP, Status}
import Utility.CreatureUtility

object Game {
  def start(participantLst: List[Creature]): Unit = {
    def play(scheduler: Scheduler): Unit = {
      scheduler.goAhead(Commander.apply)
    }

    val sortedParticipantLst = participantLst.sortWith((l, r) => l.status.speed < r.status.speed)

    play(Scheduler(sortedParticipantLst.foldLeft(Map[String, Creature]())((res, elem) => res + CreatureUtility.creatureToMapElem(elem)), MainPhase))
  }
}
