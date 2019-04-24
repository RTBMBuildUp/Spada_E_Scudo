package GameManage.Team

class Team(_name: String, _memberLst: List[String]) {
  def name: String = _name

  def memberLst: List[String] = _memberLst
}

object Team {
  def apply(_name: String, _memberLst: List[String]): Team = new Team(_name, _memberLst)
}
