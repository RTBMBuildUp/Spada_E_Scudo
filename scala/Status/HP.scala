package Status

abstract class HP(_figure: Int) extends Figure(_figure)

object HP {
  def apply(_figure: Int): HP = new ImplHp(_figure)

  class ImplHp(_figure: Int) extends HP(_figure)
}
