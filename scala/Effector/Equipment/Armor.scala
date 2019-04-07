package Effector.Equipment

import Effector.Effector
import Status.{Defence, Figure}

abstract class Armor extends Effector {
  def activate: Figure => Figure
}

case object Nakedness extends Armor {
  def activate: Figure => Figure = defence => defence
}

case object Shield extends Armor {
  def activate: Figure => Figure = defence => Defence(defence + 3)
}

case object Helmet extends Armor {
  def activate: Figure => Figure = defence => Defence(defence + 1)
}


