package Creature

import Status.{Figure, _}

abstract class Creature extends Attackable with Defendable {
  def name: String

  def status: Status

  def isAlive: Boolean = HP(0) < status.hp

  def effectLst: List[Figure => Figure]

  def addEffect(effect: Figure => Figure): Creature

  def clearEffect: Creature

  def flucstrateStatus(identifilable: Identifilable, func: Figure => Figure): Creature

  def damage(attacker: Attackable): Creature = flucstrateStatus(HP, (hp: Figure) => HP(hp - (attacker.attack - defend)))
}
