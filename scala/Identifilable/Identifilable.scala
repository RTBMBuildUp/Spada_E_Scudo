package Identifilable

trait Identifilable {
  def identify: String

  override def equals(obj: Any): Boolean = obj match {
    case o: Identifilable => o.identify == this.identify
    case _ => false
  }
}

case object None extends Identifilable {
  def identify: String = ???
}

