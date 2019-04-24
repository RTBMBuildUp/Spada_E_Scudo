package Creature

import Effector.Equipment.Equipment
import Effector.{Effector, Effectors, Spell, Transitionable}
import Identifilable.Identifilable
import Status._

class Person(_name: String, _status: Status, equipment: Equipment, _transitionableEffectorLst: List[Effector with Transitionable] = Nil) extends Creature {
  def hp: Int = _status.hp

  def attack: Int = transitionableEffectorLst.filter(_.adaptType == Attack).foldLeft(equipment.weapon.activate(this._status.attack))((res, effect) => effect.activate(res))

  def defend: Int = transitionableEffectorLst.filter(_.adaptType == Defence).foldLeft(equipment.armor.activate(this._status.defence))((res, effect) => effect.activate(res))

  def speed: Int = _status.speed

  override def spellLst: List[Spell] = Nil

  override def name: String = _name

  override def flucstrateStatus(identifilable: Identifilable, func: Int => Int): Creature = {
    val lst = _status.intMap.unzip._2.toList

    Person(
      _name,
      Status(_status.intMap + (identifilable -> func(_status.intMap(identifilable)))),
      equipment,
      transitionableEffectorLst
    )
  }

  override def transitionableEffectorLst: List[Effector with Transitionable] = _transitionableEffectorLst

  override def applyEffect(effect: Effector): Creature = effect.adaptType match {
    case transitionableEffector: Effector with Transitionable => Person(_name, _status, equipment, transitionableEffector :: transitionableEffectorLst)
    case _ => this.flucstrateStatus(effect.adaptType, effect.activate)
  }

  override def clearEffect: Creature =
    Person(
      _name,
      _status,
      equipment,
      transitionableEffectorLst.map(_.advance).filter(_ != Effectors.NoEffect)
    )

  override def toString: String = this.name

  override def equals(obj: Any): Boolean = obj match {
    case o: Person => this.name == o.name
    case _ => false
  }
}

object Person {
  def apply(name: String, stat: Status, equipment: Equipment, transitionableEffectorLst: List[Effector with Transitionable] = Nil): Person =
    new Person(name, stat, equipment, transitionableEffectorLst)
}

