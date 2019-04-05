package Effector

import Status.{Attack, Defence, Figure}

trait Effector[T] {
  def activate: T => T
}

object EffectorLst {
  case object Defend extends Effector[Figure] {
    def activate: Figure => Figure = (defence: Figure) => Defence(defence + 2)
  }

  case object TwinHits extends Effector[Figure] {
    def activate: Figure => Figure = (attack: Figure) => Attack(attack * 3 / 2)
  }
}