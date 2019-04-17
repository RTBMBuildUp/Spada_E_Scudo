package Effector.Equipment

import Effector.Effector
import Identifilable.Identifilable
import Status.Attack

abstract class Weapon extends Effector {
  override def activate: Int => Int

  override def adaptType: Identifilable = Attack
}

case object EmptyHand extends Weapon {
  override def activate: Int => Int = atk => atk
}

case object Sword extends Weapon {
  override def activate: Int => Int = atk => atk + 2
}

case object Bow extends Weapon {
  override def activate: Int => Int  = atk => atk + 1
}

