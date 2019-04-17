package Effector.Equipment

import Effector.Effector
import Identifilable.Identifilable
import Status.Defence

abstract class Armor extends Effector {
  override def activate: Int => Int

  override def adaptType: Identifilable = Defence
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


