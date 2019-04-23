package GameManage

class Team(_name: String, _memberLst: List[String]) {
  def name: String = _name

  def memberLst: List[String] = _memberLst
}

object Team {
  def apply(_name: String, _memberLst: List[String]): Team = new Team(_name, _memberLst)
}

object Mediator {
  private var _teams: List[Team] = Nil

  def teams: List[Team] = _teams

  def teams_= (team: List[Team]): Unit = {
    this._teams = team
  }

  def enemy(creatureName: String): List[Team] =
    teams.filter(!_.memberLst.contains(creatureName))

  def ally(creatureName: String): List[Team] =
    teams.filter(_.memberLst.contains(creatureName))
}