package Status

sealed class Figure(private var _figure: Int) extends Ordered[Figure] {
  _figure = if (_figure < 0) 0 else _figure

  def figure: Int = _figure

  private def calc(func: (Int, Int) => Int, right: Figure): Figure = Figure(func(this.figure, right.figure))

  def +(that: Figure): Figure = calc((l, r) => l + r, that)
  def +(that: Int): Figure = this + Figure(that)

  def -(that: Figure): Figure = calc((l, r) => l - r, that)
  def -(that: Int): Figure = this - Figure(that)

  def *(that: Figure): Figure = calc((l, r) => l * r, that)
  def *(that: Int): Figure = this * Figure(that)

  def /(that: Figure): Figure = calc((l, r) => l / r, that)
  def /(that: Int): Figure = this / Figure(that)

  override def compare(that: Figure): Int = this.figure - that.figure

  override def toString: String = this.figure.toString
}

object Figure {
  private class ImplFigure(figure: Int) extends Figure(figure)

  def apply(int: Int): Figure = new Figure(int)
  def apply(figure: Figure): Figure = figure
}

case class Attack private(_figure: Figure) extends Figure(_figure.figure)

case class Defend private(_figure: Figure) extends Figure(_figure.figure)

case class HP private(_figure: Figure) extends Figure(_figure.figure)

case class Speed private(_figure: Figure) extends Figure(_figure.figure)

case object HP extends Identifilable {
  def apply(int: Int): HP = HP(Figure(int))

  override def identificationString: String = "hp"
}

case object Attack extends Identifilable {
  def apply(int: Int): Attack = Attack(Figure(int))

  override def identificationString: String = "atk"
}

case object Defend extends Identifilable {
  def apply(int: Int): Defend = Defend(Figure(int))

  override def identificationString: String = "def"
}

case object Speed extends Identifilable {
  def apply(int: Int): Speed = Speed(Figure(int))

  override def identificationString: String = "sp"
}
