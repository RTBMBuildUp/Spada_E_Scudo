package Creature

import Status.{Figure, _}

abstract class Creature(_name: String, _status: Status) extends Attackable with Defendable {
  def name: String = _name

  def status: Status = _status

  def isAlive: Boolean = HP(0) < status.hp

  def flucstrateStatus(identifilable: Identifilable, func: Figure => Figure): Creature = {
    val lst = status.map.unzip._2.toList

    Creature(
      _name,
      Status((func(status.map(identifilable.identificationString)) :: lst.reverse).reverse)
    )
  }

  def damage(attacker: Attackable): Creature = flucstrateStatus(HP, (hp: Figure) => HP(hp - (attacker.attack - defend)))
}

object Creature {
  def apply(_name: String, _status: Status): Creature = new ImplCreature(_name, _status)

  private class ImplCreature(_name: String, _status: Status) extends Creature(_name: String, _status) {
    def attack: Attack = ???
    def defend: Defend = ???
  }
}
