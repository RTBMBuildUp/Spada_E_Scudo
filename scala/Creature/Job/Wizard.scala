package Creature.Job

import Creature.{Creature, CreatureUtility, Person}
import Effector.{Effector, Spell}
import Identifilable.Identifilable
import _root_.Effector.Equipment.Equipment
import Status.Status

class Wizard(_name: String, _status: Status, equipment: Equipment, _spellLst: List[Spell], effectLst: List[Effector] = Nil) extends Person(_name, _status, equipment, effectLst) {
  override def identify: String = "wizard"

  override def spellLst: List[Spell] = _spellLst

  override def flucstrateStatus(identificatable: Identifilable, func: Int => Int): Wizard = {
    val lst = _status.intMap.unzip._2.toList

    Wizard(
      _name,
      Status((func(_status.intMap(identificatable)) :: lst.reverse).reverse),
      equipment,
      spellLst,
      effectLst
    )
  }


  override def chant(spell: Effector, target: Creature, participantMap: Map[String, Creature]): Map[String, Creature] =
    participantMap + CreatureUtility.creatureToMapElem(target.applyEffect(spell))

  override def clearEffect: Creature = Wizard(_name, _status, equipment, _spellLst)

  override def applyEffect(effect: Effector): Creature = Wizard(_name, _status, equipment, _spellLst, effect :: effectLst)
}

object Wizard {
  def apply(name: String, status: Status, equipment: Equipment, spellLst: List[Spell], effectLst: List[Effector] = Nil) : Wizard =
    new Wizard(name, status, equipment, spellLst, effectLst)
}
