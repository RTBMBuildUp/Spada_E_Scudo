package Status

abstract class Attack(_figure: Int) extends Figure(_figure) {
  override def figure: Int = _figure

  override def toString: String = {
    "ATK:" + figure.toString
  }
}

object Attack {
  def apply(attack: Int): Attack = new ImplAttack(attack)

  private class ImplAttack(_figure: Int) extends Attack(_figure)
}

