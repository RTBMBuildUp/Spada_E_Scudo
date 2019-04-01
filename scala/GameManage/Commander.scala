package GameManage

import Creature.Creature

class Commander {
  def command[T <: Creature](creature: T, action: Action, map: Map[String, Creature]): Map[String, Creature] = action.activated(creature, map)
}

object Commander {
  def apply: Commander = new Commander()
}
