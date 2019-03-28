package Character

import Status.{Figure, _}

abstract class Character(_name: String, _status: Status) extends Attackable with Defendable {
  def name: String = _name

  def status: Status = _status

  def isAlive: Boolean = HP(0) < status.hp

  def flucstrateStatus(identifilable: Identifilable, func: Figure => Figure): Character = {
    val lst = status.map.unzip._2.toList

    Character(
      _name,
      Status((func(status.map(identifilable.identificationString)) :: lst.reverse).reverse)
    )
  }
}

object Character {
  def apply(_name: String, _status: Status): Character = new ImplCharacter(_name, _status)

  private class ImplCharacter(_name: String, _status: Status) extends Character(_name: String, _status) {
    def attack: Attack = ???
    def defend: Defend = ???
  }
}
