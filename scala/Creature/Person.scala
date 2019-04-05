package Creature

import Equipment.Equipment
import Status._

class Person(_name: String, _stat: Status, equipment: Equipment, _effectLst: List[Figure => Figure] = Nil) extends Creature {
  def attack: Attack = effectLst.foldLeft(equipment.weapon.correct(this.status.atk))((res, func) => Attack(func(res)))

  def defend: Defence = effectLst.foldLeft(equipment.armor.correct(this.status.defe))((res, func) => Defence(func(res)))

  override def name: String = _name

  override def status: Status = _stat

  override def flucstrateStatus(identifilable: Identifilable, func: Figure => Figure): Creature = {
    val lst = status.figureMap.unzip._2.toList

    Person(
      _name,
      Status((func(status.figureMap(identifilable.identificationString)) :: lst.reverse).reverse),
      equipment,
      effectLst
    )
  }

  override def effectLst: List[Figure => Figure] = _effectLst

  override def addEffect(effect: Figure => Figure): Creature = Person(_name, _stat, equipment, effect :: effectLst)

  override def clearEffect: Creature = Person(_name, _stat, equipment)

  override def toString: String = this.name

  override def equals(obj: Any): Boolean = obj match {
    case o: Person => this.name == o.name
    case _ => false
  }
}

object Person {
  def apply(name: String, stat: Status, equipment: Equipment, effectLst: List[Figure => Figure] = Nil): Person = new Person(name, stat, equipment, effectLst)
}

