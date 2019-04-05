package GameManage

import Creature.Creature

class Commander {
  def command(creature: Creature, action: Action, map: Map[String, Creature]): Map[String, Creature] = action.execute(creature, map)
}

object Commander {
  def apply: Commander = new Commander()
}
