package GameManage.FlowManage.Action

object Actions {
  private def ActionMap = Map("Attack" -> Attack, "Defend" -> Defend, "Chant" -> Chant)

  def find(key: String): Option[Action] = ActionMap.get(key)
}
