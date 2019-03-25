package Character

import Equipment.Equipment
import Status.{Attack, Defend, Figure, Status}

class Person(_name: String, _stat: Status, equipment: Equipment) extends Character(_name, _stat) {
  def attack: Attack = equipment.weapon.correct(Attack(_stat.atk.figure))

  def defend: Defend = equipment.armor.correct(Defend(_stat.defe.figure))

  override def reduceHP(receivedForce: Figure): Character = Person(name, super.reduceHP(receivedForce).status, equipment)

  override def toString: String = this.name

  override def equals(obj: Any): Boolean = obj match {
    case o: Person => this.name == o.name
    case _ => false
  }
}

object Person {
  def apply(name: String, stat: Status, equipment: Equipment): Person = new Person(name, stat, equipment)
}

