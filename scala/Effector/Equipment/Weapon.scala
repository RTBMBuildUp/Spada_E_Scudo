package Effector.Equipment

import Effector.Effector

abstract class Weapon extends Effector(1) {
  def activate: Int => Int
}

case object EmptyHand extends Weapon {
  def activate: Int => Int = atk => atk
}

case object Sword extends Weapon {
  def activate: Int => Int = atk => atk + 2
}

case object Bow extends Weapon {
  def activate: Int => Int  = atk => atk + 1
}

