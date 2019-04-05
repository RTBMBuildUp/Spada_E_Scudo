package Effector.Equipment

import Effector.Effector
import Status.Defence

abstract class Armor extends Effector[Defence] {
  def activate: Defence => Defence
}

case object Nakedness extends Armor {
  def activate: Defence => Defence = defence => defence
}

case object Shield extends Armor {
  def activate: Defence => Defence = defence => Defence(defence + 3)
}

case object Helmet extends Armor {
  def activate: Defence => Defence = defence => Defence(defence + 1)
}


