package Effector.Equipment

import Effector.Effector
import Status.{Attack, Figure}

abstract class Weapon extends Effector {
  def activate: Figure => Figure
}

case object EmptyHand extends Weapon {
  def activate: Figure => Figure = atk => atk
}

case object Sword extends Weapon {
  def activate: Figure => Figure = atk => Attack(atk.figure + 2)
}

case object Bow extends Weapon {
  def activate: Figure => Figure  = atk => Attack(atk.figure + 1)
}

