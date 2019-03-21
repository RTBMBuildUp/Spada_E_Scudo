package Status

sealed abstract class Figure(_figure: Int) extends Ordered[Figure] {
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

  private class ImplFigure(_figure: Int) extends Figure(_figure)

}

case class Attack private(_figure: Int) extends Figure(_figure)

case class Defend private(_figure: Int) extends Figure(_figure)

case class HP private(_figure: Int) extends Figure(_figure)

case class Speed private(_figure: Int) extends Figure(_figure)
