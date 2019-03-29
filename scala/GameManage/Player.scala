package GameManage

import Creature.Creature

class Player {
  def order[T <: Creature](character: T, map: Map[String, Creature], action: Action): Map[String, Creature] = action.activated(character, map)
}

object Player {
  def apply: Player = new Player()
}
