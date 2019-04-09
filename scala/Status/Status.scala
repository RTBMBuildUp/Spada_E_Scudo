package Status

import Utility.StatusUtility

class Status(lst: List[Figure]) {
  val figureMap: Map[String, Figure] =
    (StatusUtility.lst ::: lst).foldLeft(Map[String, Figure]())((res, elem) => res + (StatusUtility.identificationString(elem) -> elem))

  private def get[T <: Figure](apply: Figure => T): T = apply(figureMap(StatusUtility.identificationString(apply)))

  def hp: HP = get(HP.apply)

  def attack: Attack = get(Attack.apply)

  def defence: Defence = get(Defence.apply)

  def speed: Speed = get(Speed.apply)

  override def toString: String = figureMap.map(elem => elem._1 + ": " + elem._2).foldLeft("")((res, elem) => res + " ")
}

object Status {
  def apply(elem: List[Figure]): Status = new Status(elem)
}

