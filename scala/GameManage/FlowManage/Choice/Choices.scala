package GameManage.FlowManage.Choice

object  Choices {
  private def ChoiceMap = Map("Attack" -> Attack, "Defend" -> Defend, "Chant" -> Chant)

  def find(key: String): Option[Choice] = ChoiceMap.get(key)
}