package Creature

import Effector.Effector
import Status.{Figure, _}

abstract class Creature extends Attackable with Defendable {
  def name: String

  def hp: HP

  def speed: Speed

  def isAlive: Boolean = HP(0) < hp

  def effectLst: List[Effector[Figure]]

  def addEffect(effect: Effector[Figure]): Creature

  def clearEffect: Creature

  def flucstrateStatus(identifilable: Identifilable, effect: Figure => Figure): Creature

  def damage(attacker: Attackable): Creature = flucstrateStatus(HP, (hp: Figure) => HP(hp - (attacker.attack - defend)))
}
