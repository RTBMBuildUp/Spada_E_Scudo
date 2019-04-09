package Effector

import Status.{Attack, Defence, Figure, Identifilable}

abstract class Effector(private val _duration: Int = 1) {
  protected val duration: Int = if (_duration < 1) 1 else _duration

  def activate: Figure => Figure

  def advance: Effector = EffectorLst.NoEffect
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
  case object Defend extends Effector {
    def activate: Figure => Figure = (defence: Figure) => Defence(defence + 2)
  }

  case object NoEffect extends Effector {
    def activate: Figure => Figure = ???
  }

  case object TwinHits extends Spell(2) {
    def activate: Figure => Figure = (attack: Figure) => Attack(attack * 3 / 2)

    override def advance: Effector = duration match {
      case 1 => NoEffect
      case num => Spell(num - 1, activate, TwinHits, identificationString)
    }

    def identificationString: String = "twin_hits"
  }
}