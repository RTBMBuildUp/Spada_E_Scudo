package GameManage

import Creature.Creature

class Player {
  def order[T <: Creature](creature: T, action: Action, map: Map[String, Creature]): Map[String, Creature] = action.activated(creature, map)
}

object Player {
  def apply: Player = new Player()
}
