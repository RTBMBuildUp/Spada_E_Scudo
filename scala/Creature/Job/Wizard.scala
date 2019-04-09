package Creature.Job

import Creature.{Creature, Person}
import Effector.{Effector, Spell}
import _root_.Effector.Equipment.Equipment
import Status.Status
import Utility.CreatureUtility

class Wizard(_name: String, _status: Status, equipment: Equipment, _spellLst: List[Spell], effectLst: List[Effector] = Nil) extends Person(_name, _status, equipment, effectLst) {
  override def spellLst: List[Spell] = _spellLst

  override def chant(spell: Effector, target: Creature, participantMap: Map[String, Creature]): Map[String, Creature] =
    participantMap + CreatureUtility.creatureToMapElem(target.addEffect(spell))

}

object Wizard {
  def apply(name: String, status: Status, equipment: Equipment, spellLst: List[Spell], effectLst: List[Effector] = Nil) : Wizard =
    new Wizard(name, status, equipment, spellLst, effectLst)
}
