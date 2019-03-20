package Status

abstract class Speed(_figure: Int) extends Figure(_figure)

object Speed {
  def apply(_figure: Int): Speed = new ImplSpeed(_figure)

  class ImplSpeed(_figure: Int) extends Speed(_figure)
}