package GameManage.Team

object Mediator {
  private var _teams: List[Team] = Nil

  def teams_= (team: List[Team]): Unit = {
    this._teams = team
  }

  def enemy(creatureName: String): List[Team] =
    _teams.filter(!_.memberLst.contains(creatureName))

  def ally(creatureName: String): List[Team] =
    _teams.filter(_.memberLst.contains(creatureName))
}