package Creature

import Effector.{Effector, EffectorLst, Spell}
import Identifilable.Identifilable
import Status._

class Monster(_name: String, _status: Status, _effectLst: List[Effector] = Nil) extends Creature {
  override def identify: String = "monster"

  def hp: Int = _status.hp

  def attack: Int = this._status.attack

  def defend: Int = this._status.defence

  def speed: Int = this._status.speed

  override def spellLst: List[Spell] = Nil

  override def name: String = _name

  override def flucstrateStatus(identificatable: Identifilable, func: Int => Int): Creature = {
    val lst = _status.intMap.unzip._2.toList

    Monster(
      _name,
      Status((func(_status.intMap(identificatable.identify)) :: lst.reverse).reverse),
      effectLst
    )
  }

  override def effectLst: List[Effector] = _effectLst

  override def addEffect(effect: Effector): Creature = Monster(name, _status, effect :: effectLst)

  override def clearEffect: Creature =
    Monster(
      name,
      _status,
      effectLst.map(effect => effect.advance).filter(_ != EffectorLst.NoEffect)
    )
}

object Monster {
  def apply(name: String, status: Status, effectLst: List[Effector] = Nil): Monster = new Monster(name, status, effectLst)
}