package Effector

import Identifilable.Identifilable
import Status._

abstract class Effector(private val _duration: Int = 1) {
  protected val duration: Int = if (_duration < 1) 1 else _duration

  def adaptType: Identifilable = ???

  def activate: Int => Int

  def advance: Effector = Effectors.NoEffect

  def advance(apply: Int => Effector): Effector = duration match {
    case 1 => Effectors.NoEffect
    case num => apply(num - 1)
  }
}

abstract class Spell(duration: Int) extends Effector(duration) with Identifilable {
  def startMessage: String
}

object Spell {
  def apply(duration: Int, _adaptType: Identifilable, activateFunc: Int => Int, _stateTransition: Effector, _spellName: String, _message: String): Spell = new Spell(duration) {
    override def adaptType: Identifilable = _adaptType

    override def activate: Int => Int = activateFunc

    override def advance: Effector = _stateTransition

    override def identify: String = _spellName

    override def startMessage: String = _message
  }
}

object Effectors {
  case object NoEffect extends Effector {
    override def activate: Int => Int = ???
  }

  case class Defend(_duration: Int = 1) extends Effector(_duration) {
    override def adaptType: Identifilable = Defence

    override def activate: Int => Int = (defence: Int) => defence + 2
  }

  case class TwinHits(_duration: Int = 2) extends Spell(_duration) {
    override def adaptType: Identifilable = Attack

    override def activate: Int => Int = (attack: Int) => attack * 3 / 2

    override def advance: Effector = duration match {
      case 1 => NoEffect
      case num => TwinHits(num - 1)
    }

    override def identify: String = "twin_hits"

    override def startMessage: String = "攻撃力がアップ"
  }
}