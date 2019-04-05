package Creature

import Status._

class Monster(_name: String, _status: Status, _effectLst: List[Figure => Figure] = Nil) extends Creature {
  def hp: HP = _status.hp

  def attack: Attack = this._status.attack

  def defend: Defence = this._status.defence

  def speed: Speed = this._status.speed

  override def name: String = _name

  override def flucstrateStatus(identifilable: Identifilable, func: Figure => Figure): Creature = {
    val lst = _status.figureMap.unzip._2.toList

    Monster(
      _name,
      Status((func(_status.figureMap(identifilable.identificationString)) :: lst.reverse).reverse),
      effectLst
    )
  }

  override def effectLst: List[Figure => Figure] = _effectLst

  override def addEffect(effect: Figure => Figure): Creature = Monster(name, _status, effect :: effectLst)

  override def clearEffect: Creature = Monster(name, _status)
}

object Monster {
  def apply(name: String, status: Status, effectLst: List[Figure => Figure] = Nil): Monster = new Monster(name, status, effectLst)
}