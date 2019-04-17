package Effector

import Identifilable.Identifilable
import Status._

trait Effector {
  def activate: Int => Int

  def adaptType: Identifilable
}

trait Transitionable {
  def advance: Effector with Transitionable
}

trait Spell extends Effector with Identifilable

object Effectors {
  object NoEffect extends Effector with Transitionable {
    override def activate: Int => Int = ???

    override def adaptType: Identifilable = ???

    override def advance: Effector with Transitionable = NoEffect
  }

  case object Defend extends Effector {
    override def activate: Int => Int = (defence: Int) => defence + 2

    override def adaptType: Identifilable = Defence
  }

  case object Frizz extends Spell {
    override def activate: Int => Int = (hp: Int) => hp - 10

    override def adaptType: Identifilable = HP

    override def identify: String = "frizz"
  }

  case class Acceleratle(_duration: Int = 3) extends Spell with Transitionable {
    def duration: Int = if (_duration < 0) 0 else duration

    override def activate: Int => Int = (speed: Int) => speed + 2

    override def adaptType: Identifilable = Speed

    override def identify: String = "Acceleratle"

    override def advance: Effector with Transitionable = duration match {
      case 0 => NoEffect
      case num => Acceleratle(num - 1)
    }
  }

  case class Oomph(_duration: Int = 2) extends Spell with Transitionable {
    def duration: Int = if (_duration < 0) 0 else _duration

    override def activate: Int => Int = (attack: Int) => attack * 3 / 2

    override def adaptType: Identifilable = Attack

    override def identify: String = "oomph"

    override def advance: Effector with Transitionable = duration match {
      case 0 => NoEffect
      case num => Oomph(num - 1)
    }
  }

}
