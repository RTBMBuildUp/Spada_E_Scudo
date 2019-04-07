package Creature.Job

import Creature.{Creature, Person}
import Effector.{Effector, Spell}
import _root_.Effector.Equipment.Equipment
import Status.{Figure, Status}

class Wizard(_name: String, _status: Status, equipment: Equipment, _spellLst: List[Spell], effectLst: List[Effector] = Nil) extends Person(_name, _status, equipment, effectLst) {
  override def spellLst: List[Spell] = _spellLst

  override def chant(spell: Effector, participantMap: Map[String, Creature]): Map[String, Creature] = {
    ???
  }
}
