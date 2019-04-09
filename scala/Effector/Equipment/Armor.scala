package Effector.Equipment

import Effector.Effector

abstract class Armor extends Effector {
  def activate: Int => Int
}

case object Nakedness extends Armor {
  def activate: Int => Int = defence => defence
}

case object Shield extends Armor {
  def activate: Int => Int = defence => defence + 3
}

case object Helmet extends Armor {
  def activate: Int => Int = defence => defence + 1
}


