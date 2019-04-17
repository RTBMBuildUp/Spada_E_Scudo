package Creature

import Effector.{Effector, Effectors, Spell, Transitionable}
import Identifilable.Identifilable
import Status._

class Monster(_name: String, _status: Status, _transitionableEffectLst: List[Effector with Transitionable] = Nil) extends Creature {
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
      Status(_status.intMap + (identificatable -> func(_status.intMap(identificatable)))),
      transitionableEffectorLst
    )
  }

  override def transitionableEffectorLst: List[Effector with Transitionable] = _transitionableEffectLst

  override def applyEffect(effect: Effector): Creature = effect match {
    case transitionableEffector: Effector with Transitionable => Monster(_name, _status, transitionableEffector :: transitionableEffectorLst)
    case _ => this.flucstrateStatus(effect.adaptType, effect.activate)
  }

  override def clearEffect: Creature =
    Monster(
      name,
      _status,
      transitionableEffectorLst.map(effect => effect.advance).filter(_ != Effectors.NoEffect)
    )
}

object Monster {
  def apply(name: String, status: Status, transitionableEffectLst: List[Effector with Transitionable] = Nil): Monster = new Monster(name, status, transitionableEffectLst)
}