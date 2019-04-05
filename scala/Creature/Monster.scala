package Creature

import Status._

class Monster(_name: String, _status: Status, _effectLst: List[Figure => Figure] = Nil) extends Creature {

  def attack: Attack = this.status.attack

  def defend: Defence = this.status.defence

  override def name: String = _name

  override def status: Status = _status

  override def flucstrateStatus(identifilable: Identifilable, func: Figure => Figure): Creature = {
    val lst = status.figureMap.unzip._2.toList

    Monster(
      _name,
      Status((func(status.figureMap(identifilable.identificationString)) :: lst.reverse).reverse),
      effectLst
    )
  }

  override def effectLst: List[Figure => Figure] = _effectLst

  override def addEffect(effect: Figure => Figure): Creature = Monster(name, status, effect :: effectLst)

  override def clearEffect: Creature = Monster(name, status)

  private def creatureToThis(creature: Creature): Monster = Monster(creature.name, creature.status, creature.effectLst)
}

object Monster {
  def apply(name: String, status: Status, effectLst: List[Figure => Figure] = Nil): Monster = new Monster(name, status, effectLst)
}