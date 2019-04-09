package Identifilable

trait Identifilable {
  def identify: String
}

case object None extends Identifilable {
  def identify: String = ???
}

