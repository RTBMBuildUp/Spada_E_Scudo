package Equipment

import Status.Defence

abstract class Armor {
  def correct: Defence => Defence
}

case object Nakedness extends Armor {
  def correct: Defence => Defence = defe => defe
}

case object Shield extends Armor {
  def correct: Defence => Defence = defe => Defence(defe.figure + 3)
}

case object Helmet extends Armor {
  def correct: Defence => Defence = defe => Defence(defe.figure + 1)
}


