package Equipment

import Status.Defend

abstract class Armor {
  def correct: Defend => Defend
}

case object Nakedness extends Armor {
  def correct: Defend => Defend = defe => defe
}

case object Shield extends Armor {
  def correct: Defend => Defend = defe => Defend(defe.figure + 3)
}

case object Helmet extends Armor {
  def correct: Defend => Defend = defe => Defend(defe.figure + 1)
}


