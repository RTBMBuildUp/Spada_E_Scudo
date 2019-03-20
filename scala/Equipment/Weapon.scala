package Equipment

import Status.Attack

abstract class Weapon() {
  def correct: Attack => Attack
}

case object EmptyHand extends Weapon {
  def correct: Attack => Attack = atk => atk
}

case object Sword extends Weapon {
  def correct: Attack => Attack = atk => Attack(atk.figure + 2)
}

case object Bow extends Weapon {
  def correct: Attack => Attack = atk => Attack(atk.figure + 1)
}

