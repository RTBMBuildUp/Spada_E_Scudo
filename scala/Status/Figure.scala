package Status

import Character.Identifilable

sealed abstract class Figure(_figure: Int) extends Ordered[Figure] with Identifilable {
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

object Figure {
  def apply(_figure: Int): Figure = new ImplFigure(_figure)
//  def apply(figure: Figure): Figure = new ImplFigure(figure.figure)

  private class ImplFigure(_figure: Int) extends Figure(_figure) {
    override def identificationString: String = "impl"
  }

}

case class Attack private(_figure: Int) extends Figure(_figure) {
  override def identificationString:String = "atk"
}

case class Defend private(_figure: Int) extends Figure(_figure) {
  override def identificationString: String = "def"
}

case class HP private(_figure: Int) extends Figure(_figure) {
  override def identificationString: String = "hp"
}

case class Speed private(_figure: Int) extends Figure(_figure) {
  override def identificationString: String = "sp"
}

case class ChargeTime private(_figure: Int) extends Figure(_figure) {
  def reset: ChargeTime = ChargeTime(0)

  override def identificationString: String = "ct"
}