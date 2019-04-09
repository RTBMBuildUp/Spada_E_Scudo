package Creature

import Effector.{Effector, EffectorLst}
import Status._
import Utility.StatusUtility

class Monster(_name: String, _status: Status, _effectLst: List[Effector] = Nil) extends Creature {
  def hp: HP = _status.hp

  def attack: Attack = this._status.attack

  def defend: Defence = this._status.defence

  def speed: Speed = this._status.speed

  override def name: String = _name

  override def flucstrateStatus(func: Figure => Figure): Creature = {
    val lst = _status.figureMap.unzip._2.toList

    Monster(
      _name,
      Status((func(_status.figureMap(StatusUtility.identificationString(func))) :: lst.reverse).reverse),
      effectLst
    )
  }

  override def effectLst: List[Effector] = _effectLst

  override def addEffect(effect: Effector): Creature = Monster(name, _status, effect :: effectLst)

  override def clearEffect: Creature = Monster(name, _status, effectLst.filter(effect => effect.advance != EffectorLst.NoEffect))
}

object Monster {
  def apply(name: String, status: Status, effectLst: List[Effector] = Nil): Monster = new Monster(name, status, effectLst)
}