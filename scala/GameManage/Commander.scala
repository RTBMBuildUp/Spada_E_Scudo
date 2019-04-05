package GameManage

import Creature.Creature

class Commander {
  def command(creature: Creature, action: Action, participantMap: Map[String, Creature]): Map[String, Creature] = action.execute(creature, participantMap)
}

object Commander {
  def apply: Commander = new Commander()
}
