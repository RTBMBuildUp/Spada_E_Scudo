package Effector.Equipment

import Effector.Effector
import Status.{Attack, Figure}

abstract class Weapon extends Effector(1) {
  def activate: Figure => Figure
}

case object EmptyHand extends Weapon {
  def activate: Figure => Figure = atk => atk
}

case object Sword extends Weapon {
  def activate: Figure => Figure = atk => Attack(atk.number + 2)
}

case object Bow extends Weapon {
  def activate: Figure => Figure  = atk => Attack(atk.number + 1)
}

