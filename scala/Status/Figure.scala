package Status

import Character.Identifilable

sealed abstract class Figure(private var _figure: Int) extends Ordered[Figure] {
  _figure = if (_figure < 0) 0 else _figure

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
