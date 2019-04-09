package Effector

import Identifilable.Identifilable

abstract class Effector(private val _duration: Int = 1) {
  protected val duration: Int = if (_duration < 1) 1 else _duration

  def activate: Int => Int

  def advance: Effector = EffectorLst.NoEffect

  def advance(apply: Int => Effector): Effector = duration match {
    case 1 => EffectorLst.NoEffect
    case num => apply(num - 1)
  }
}

abstract class Spell(duration: Int) extends Effector(duration) with Identifilable

object Spell {
  def apply(duration: Int, activateFunc: Int => Int, stateTransition: Effector, spellName: String): Spell = new Spell(duration) {
    override def activate: Int => Int = activateFunc

    override def advance: Effector = stateTransition

    override def identify: String = spellName
  }
}

object EffectorLst {
  case object NoEffect extends Effector {
    def activate: Int => Int = ???
  }

  case class Defend(_duration: Int = 1) extends Effector(_duration) {
    def activate: Int => Int = (defence: Int) => defence + 2
  }

  case class TwinHits(_duration: Int = 2) extends Spell(_duration) {
    def activate: Int => Int = (attack: Int) => attack * 3 / 2

    override def advance: Effector = duration match {
      case 1 => NoEffect
      case num => TwinHits(num - 1)
    }

    def identify: String = "twin_hits"
  }
}