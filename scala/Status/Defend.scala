package Status

abstract class Defend(_figure: Int) extends Figure(_figure) {
  override def figure: Int = _figure

  override def toString: String = {
    "DEF:" + figure
  }
}

object Defend {
  def apply(_figure: Int): Defend = new ImplDefend(_figure)

  private class ImplDefend(_figure: Int) extends Defend(_figure)

}
