package Creature

import Effector.{Effector, Spell}
import Identifilable.Identifilable
import Status.HP

abstract class Creature extends Attackable with Defendable with Identifilable {
  def name: String

  def hp: Int

  def speed: Int

  def isAlive: Boolean = 0 < hp

  def effectLst: List[Effector]

  def spellLst: List[Spell]

  def chant(spell: Effector, target: Creature, participantMap: Map[String, Creature]): Map[String, Creature] = participantMap

  def addEffect(effect: Effector): Creature

  def clearEffect: Creature

  def flucstrateStatus(identificatable: Identifilable, func: Int => Int): Creature

  def damage(attacker: Attackable): Creature = {
    println(this + "は" + (defend - attacker.attack) + "のダメージを受けた。")
    flucstrateStatus(HP, (hp: Int) => hp - (defend - attacker.attack))
  }
}
