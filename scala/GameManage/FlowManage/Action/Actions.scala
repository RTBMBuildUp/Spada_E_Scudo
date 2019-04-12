package GameManage.FlowManage.Action

import Identifilable.Identifilable

object Actions {
  def lst: List[Action with Identifilable] = List(Attack, Defend, Chant)
}
