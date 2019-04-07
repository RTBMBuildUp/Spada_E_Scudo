package Creature

import Effector.Equipment.Equipment
import Effector.Effector
import Status._

class Person(_name: String, _status: Status, equipment: Equipment, _effectLst: List[Effector] = Nil) extends Creature {
  def hp: HP = _status.hp

  def attack: Attack = effectLst.foldLeft(Attack(equipment.weapon.activate(this._status.attack)))((res, effect) => Attack(effect.activate(res)))

  def defend: Defence = effectLst.foldLeft(Defence(equipment.armor.activate(this._status.defence)))((res, effect) => Defence(effect.activate(res)))

  def speed: Speed = _status.speed

  override def name: String = _name

  override def flucstrateStatus(identifilable: Identifilable, func: Figure => Figure): Creature = {
    val lst = _status.figureMap.unzip._2.toList

    Person(
      _name,
      Status((func(_status.figureMap(identifilable.identificationString)) :: lst.reverse).reverse),
      equipment,
      effectLst
    )
  }

  override def effectLst: List[Effector] = _effectLst

  override def addEffect(effect: Effector): Creature = Person(_name, _status, equipment, effect :: effectLst)

  override def clearEffect: Creature = Person(_name, _status, equipment)

  override def toString: String = this.name

  override def equals(obj: Any): Boolean = obj match {
    case o: Person => this.name == o.name
    case _ => false
  }
}

object Person {
  def apply(name: String, stat: Status, equipment: Equipment, effectLst: List[Effector] = Nil): Person = new Person(name, stat, equipment, effectLst)
}

