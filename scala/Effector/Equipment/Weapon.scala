package Effector.Equipment

import Effector.Effector
import Status.Attack

abstract class Weapon extends Effector[Attack] {
  def activate: Attack => Attack
}

case object EmptyHand extends Weapon {
  def activate: Attack => Attack = atk => atk
}

case object Sword extends Weapon {
  def activate: Attack => Attack = atk => Attack(atk.figure + 2)
}

case object Bow extends Weapon {
  def activate: Attack => Attack = atk => Attack(atk.figure + 1)
}

