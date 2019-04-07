package Effector

import Status.{Attack, Defence, Figure, Identifilable}

abstract class Effector {
  def activate: Figure => Figure

  protected def duration: Int = 1

  protected def advance: Effector = duration match {
    case 1 => EffectorLst.NoEffect
    case _ =>
  }
}

trait Spell extends Effector with Identifilable

object EffectorLst {
  case object Defend extends Effector {
    def activate: Figure => Figure = (defence: Figure) => Defence(defence + 2)
  }

  case object NoEffect extends Effector {
    def activate: Figure => Figure = ???
  }

  case object TwinHits extends Spell {
    def activate: Figure => Figure = (attack: Figure) => Attack(attack * 3 / 2)

    def identificationString: String = "twin_hits"
  }
}