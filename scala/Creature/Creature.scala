package Creature

import Effector.{Effector, Spell, Transitionable}
import GameManage.ParticipantMap.ParticipantMap
import Identifilable.Identifilable
import Status.HP

abstract class Creature extends Attackable with Defendable {
  def name: String

  def hp: Int

  def speed: Int

  def isAlive: Boolean = 0 < hp

  def transitionableEffectorLst: List[Effector with Transitionable]

  def spellLst: List[Spell]

  def chant(spell: Spell, target: Creature, participantMap: ParticipantMap): ParticipantMap = participantMap

  def applyEffect(effect: Effector): Creature

  def clearEffect: Creature

  def flucstrateStatus(identificatable: Identifilable, func: Int => Int): Creature

  def damage(attacker: Attackable): Creature = {
    def toPositive(int: Int): Int = int match {
      case num if num <= 0 => 0
      case num => num
    }

    val positive = toPositive(attacker.attack - defend)

    println(this + "は" + positive + "のダメージを受けた。")
    flucstrateStatus(HP, (hp: Int) => hp - positive)
  }
}
