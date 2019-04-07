package Status

sealed class Figure(private val _figure: Int) extends Ordered[Figure] {
  private val figure = if (_figure < 0) 0 else _figure

  def number: Int = figure

  private def calc(func: (Int, Int) => Int, right: Figure): Figure = Figure(func(this.number, right.number))

  def +(that: Figure): Figure = calc((l, r) => l + r, that)
  def +(that: Int): Figure = this + Figure(that)

  def -(that: Figure): Figure = calc((l, r) => l - r, that)
  def -(that: Int): Figure = this - Figure(that)

  def *(that: Figure): Figure = calc((l, r) => l * r, that)
  def *(that: Int): Figure = this * Figure(that)

  def /(that: Figure): Figure = calc((l, r) => l / r, that)
  def /(that: Int): Figure = this / Figure(that)

  override def compare(that: Figure): Int = this.number - that.number

  override def toString: String = this.number.toString
}

object Figure {
  private class ImplFigure(figure: Int) extends Figure(figure)

  def apply(int: Int): Figure = new Figure(int)
  def apply(figure: Figure): Figure = figure
}

case class HP private(_figure: Figure) extends Figure(_figure.number)

case class Attack private(_figure: Figure) extends Figure(_figure.number)

case class Defence private(_figure: Figure) extends Figure(_figure.number)

case class Speed private(_figure: Figure) extends Figure(_figure.number)

case object HP extends Identifilable {
  def apply(int: Int): HP = HP(Figure(int))

  override def identificationString: String = "hp"
}

case object Attack extends Identifilable {
  def apply(int: Int): Attack = Attack(Figure(int))

  override def identificationString: String = "attack"
}

case object Defence extends Identifilable {
  def apply(int: Int): Defence = Defence(Figure(int))

  override def identificationString: String = "defence"
}

case object Speed extends Identifilable {
  def apply(int: Int): Speed = Speed(Figure(int))

  override def identificationString: String = "sp"
}
