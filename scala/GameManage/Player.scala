package GameManage

import Character.Character

class Player {
  def order[T <: Character](character: T, action: T => Map[String, Character]): Map[String, Character] = action(character)
}

object Player {
  def apply: Player = new Player()
}
