package GameManage

import Creature.{Creature, CreatureUtility}
import GameManage.FlowManage.Phase.MainPhase
import GameManage.FlowManage.Scheduler

object Game {
  def start(participantLst: List[Creature]): Unit = {
    def process(scheduler: Scheduler): Unit = scheduler.goAhead() match {
      case nextSchedule if nextSchedule.participantMap.toList.unzip._2.count(_.isAlive) == 1 => println("終了")
      case nextShedule => process(nextShedule)
    }

    val sortedParticipantLst = participantLst.sortWith((l, r) => l.speed < r.speed)

    process(Scheduler(sortedParticipantLst.foldLeft(Map[String, Creature]())((res, elem) => res + CreatureUtility.creatureToMapElem(elem)), MainPhase))
  }
}
