package GameManage

import Creature.Creature

class TurnSheduler(_participantsMap: Map[String, Creature], _actionLst: List[Action]) {
  def participantsMap: Map[String, Creature] = _participantsMap
  def actionLst: List[Action] = _actionLst
}
