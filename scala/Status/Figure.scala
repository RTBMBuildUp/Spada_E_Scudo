package Status

import Character.Identifilable

sealed abstract class Figure(_figure: Int) extends Ordered[Figure] {
  if (_figure < 0) Figure(0)

  def figure: Int = _figure

  private def calc(func: (Int, Int) => Int, right: Figure): Figure = Figure(func(this.figure, right.figure))

  def +(that: Figure): Figure = calc((l, r) => l + r, that)

  def -(that: Figure): Figure = calc((l, r) => l - r, that)

  def *(that: Figure): Figure = calc((l, r) => l * r, that)

  def /(that: Figure): Figure = calc((l, r) => l / r, that)

  override def compare(that: Figure): Int = this.figure - that.figure

  override def toString: String = this.figure.toString
}

private object Figure {
  def apply(_figure: Int): Figure = new ImplFigure(_figure)

  private class ImplFigure(_figure: Int) extends Figure(_figure)
}

case class Attack private(_figure: Int) extends Figure(_figure)

case class Defend private(_figure: Int) extends Figure(_figure)

case class HP private(_figure: Int) extends Figure(_figure)

case class Speed private(_figure: Int) extends Figure(_figure)

case class ChargeTime private(_figure: Int) extends Figure(_figure) {
  def reset: ChargeTime = ChargeTime(0)
}

object Utility {
  def lst: List[Figure] = List[Figure](HP(0), Attack(0), Defend(0), Speed(0), ChargeTime(0))

  def identificationString(figure: Figure): String = (figure match {
    case _: HP => HP
    case _: Attack => Attack
    case _: Defend => Defend
    case _: Speed => Speed
    case _: ChargeTime => ChargeTime
  }).identificationString
}

case object HP extends Identifilable {
  override def identificationString: String = "hp"
}

case object Attack extends Identifilable {
  override def identificationString: String = "atk"
}

case object Defend extends Identifilable {
  override def identificationString: String = "def"
}

case object Speed extends Identifilable {
  override def identificationString: String = "sp"
}

case object ChargeTime extends Identifilable {
  override def identificationString: String = "ct"
}


