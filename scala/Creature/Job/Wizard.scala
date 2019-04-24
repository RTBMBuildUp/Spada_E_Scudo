package Creature.Job

import Creature.{Creature, CreatureUtility, Person}
import Effector.{Effector, Spell, Transitionable}
import GameManage.ParticipantMap.ParticipantMap
import Identifilable.Identifilable
import _root_.Effector.Equipment.Equipment
import Status.Status

class Wizard(_name: String, _status: Status, equipment: Equipment, _spellLst: List[Spell], _transitionableEffectorLst: List[Effector with Transitionable] = Nil) extends Person(_name, _status, equipment, _transitionableEffectorLst) {
  override def spellLst: List[Spell] = _spellLst

  override def flucstrateStatus(identificatable: Identifilable, func: Int => Int): Wizard = {
    val lst = _status.intMap.unzip._2.toList

    Wizard(
      _name,
      Status(_status.intMap + (identificatable -> func(_status.intMap(identificatable)))),
      equipment,
      spellLst,
      _transitionableEffectorLst
    )
  }

  override def chant(spell: Spell, target: Creature, participantMap: ParticipantMap): ParticipantMap =
    participantMap + CreatureUtility.creatureToMapElem(target.applyEffect(spell))

  override def clearEffect: Creature = Wizard(_name, _status, equipment, _spellLst)

  override def applyEffect(effect: Effector): Creature = effect match {
    case transitionableEffect: Effector with Transitionable => Wizard(_name, _status, equipment, _spellLst, transitionableEffect :: _transitionableEffectorLst)
    case _ => this.flucstrateStatus(effect.adaptType, effect.activate)
  }
}

object Wizard {
  def apply(name: String, status: Status, equipment: Equipment, spellLst: List[Spell], transitionableEffectorLst: List[Effector with Transitionable] = Nil) : Wizard =
    new Wizard(name, status, equipment, spellLst, transitionableEffectorLst)
}
