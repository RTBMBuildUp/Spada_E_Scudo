package Status

trait Identifilable {
  def identify: String
}

case object None extends Identifilable {
  def identify: String = ???
}

case object HP extends Identifilable {
  def identify: String = "HP"
}

case object Attack extends Identifilable {
  def identify: String = "Attack"
}

case object Defence extends Identifilable {
  def identify: String = "Defence"
}

case object Speed extends Identifilable {
  def identify: String = "Speed"
}
