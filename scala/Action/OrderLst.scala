package Action

import Identifilable.Identifilable

object OrderLst {
  def lst: List[Action with Identifilable] = List(Attack, Defend, Chant)
}
