package Creature

import Effector.Equipment.Equipment
import Effector.{Effector, EffectorLst}
import Status._
import Utility.StatusUtility

class Person(_name: String, _status: Status, equipment: Equipment, _effectLst: List[Effector] = Nil) extends Creature {
  def hp: Int = _status.hp

  def attack: Int = effectLst.foldLeft(equipment.weapon.activate(this._status.attack))((res, effect) => effect.activate(res))

  def defend: Int = effectLst.foldLeft(equipment.armor.activate(this._status.defence))((res, effect) => effect.activate(res))

  def speed: Int = _status.speed

  override def name: String = _name

  override def flucstrateStatus(identificatable: Identifilable, func: Int => Int): Creature = {
    val lst = _status.intMap.unzip._2.toList

    Person(
      _name,
      Status((func(_status.intMap(identificatable.identify)) :: lst.reverse).reverse),
      equipment,
      effectLst
    )
  }

  override def effectLst: List[Effector] = _effectLst

  override def addEffect(effect: Effector): Creature = Person(_name, _status, equipment, effect :: effectLst)

  override def clearEffect: Creature =
    Person(
      _name,
      _status,
      equipment,
      effectLst.map(_.advance).filter(_ != EffectorLst.NoEffect)
    )

  override def toString: String = this.name

  override def equals(obj: Any): Boolean = obj match {
    case o: Person => this.name == o.name
    case _ => false
  }
}

object Person {
  def apply(name: String, stat: Status, equipment: Equipment, effectLst: List[Effector] = Nil): Person = new Person(name, stat, equipment, effectLst)
}

