package Status

import Identifilable.Identifilable

class Status(_intMap: Map[Identifilable, Int]) {
  val intMap: Map[Identifilable, Int] = _intMap

  private def get(key: Identifilable): Int = intMap.toList.filter(_._1 == key) match {
    case Nil => 0
    case (_, value) :: _ => value
  }

  def hp: Int = get(HP)

  def attack: Int = get(Attack)

  def defence: Int = get(Defence)

  def speed: Int = get(Speed)

  override def toString: String = intMap.map(elem => elem._1 + ": " + elem._2).foldLeft("")((res, elem) => res + " ")
}

object Status {
  def apply(lst: List[Int]): Status =
    new Status(StatusUtility.lst.zip(lst).map(tuple => (tuple._1, tuple._2)).toMap)

  def apply(identifilableMap: Map[Identifilable, Int]): Status = new Status(identifilableMap)
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
