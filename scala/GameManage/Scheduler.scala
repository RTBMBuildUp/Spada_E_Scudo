package GameManage

import Creature.Creature

class Scheduler(_participantsMap: Map[String, Creature], _actionLst: List[Action]) {
  def participantsMap: Map[String, Creature] = _participantsMap
  def actionLst: List[Action] = _actionLst
}

object Scheduler {
  def apply(participantMap: Map[String, Creature], actionLst: List[Action]): Scheduler =
    new Scheduler(participantMap, actionLst)
}
