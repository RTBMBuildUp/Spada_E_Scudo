package GameManage

import Creature.Creature

class Player {
  def order[T <: Creature](creature: T, map: Map[String, Creature], action: Action): Map[String, Creature] = action.activated(creature, map)
}

object Player {
  def apply: Player = new Player()
}
