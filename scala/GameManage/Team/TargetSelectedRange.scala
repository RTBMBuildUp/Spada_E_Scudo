package GameManage.Team

trait TargetSelectedRange {
  def range(creatureName: String): List[String]
}

case object NoneTarget extends TargetSelectedRange {
  override def range(creatureName: String): List[String] = Nil
}

case object EnemyOnly extends TargetSelectedRange {
  override def range(creatureName: String): List[String] =
    Mediator.enemy(creatureName).foldLeft(List[String]())((res, team) => team.memberLst ::: res)
}

case object AllyOnly extends TargetSelectedRange {
  override def range(creatureName: String): List[String] =
    Mediator.ally(creatureName).foldLeft(List[String]())((res, team) => team.memberLst ::: res)
}

case object EnemyAndAlly extends TargetSelectedRange {
  override def range(creatureName: String): List[String] =
    (Mediator.ally(creatureName) ::: Mediator.enemy(creatureName)).foldLeft(List[String]())((res, team) => team.memberLst ::: res)
}
