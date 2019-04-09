package Effector

import Status.{Attack, Defence, Figure, Identifilable}

abstract class Effector(private val _duration: Int = 1) {
  protected val duration: Int = if (_duration < 1) 1 else _duration

  def activate: Figure => Figure

  def advance: Effector = EffectorLst.NoEffect

  def advance(apply: Int => Effector): Effector = duration match {
    case 1 => EffectorLst.NoEffect
    case num => apply(num - 1)
  }
}

abstract class Spell(duration: Int) extends Effector(duration) with Identifilable

object Spell {
  def apply(duration: Int, activateFunc: Figure => Figure, stateTransition: Effector, spellName: String): Spell = new Spell(duration) {
    override def activate: Figure => Figure = activateFunc

    override def advance: Effector = stateTransition

    override def identificationString: String = spellName
  }
}

object EffectorLst {
  case object NoEffect extends Effector {
    def activate: Figure => Figure = ???
  }

  case class Defend(_duration: Int = 1) extends Effector(_duration) {
    def activate: Figure => Figure = (defence: Figure) => Defence(defence + 2)
  }

  case class TwinHits(_duration: Int = 2) extends Spell(_duration) {
    def activate: Figure => Figure = (attack: Figure) => Attack(attack * 3 / 2)

    override def advance: Effector = duration match {
      case 1 => NoEffect
      case num => TwinHits(num - 1)
    }

    def identificationString: String = "twin_hits"
  }
}