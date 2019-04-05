package Status

import Utility.StatusUtility

class Status(lst: List[Figure]) {
  val figureMap: Map[String, Figure] =
    (StatusUtility.lst ::: lst).foldLeft(Map[String, Figure]())((res, elem) => res + (StatusUtility.identificationString(elem) -> elem))

  private def get[T <: Figure](identifilable: Identifilable, apply: Figure => T): T = apply(figureMap(identifilable.identificationString))

  def hp: HP = get(HP, HP.apply)

  def attack: Attack = get(Attack, Attack.apply)

  def defence: Defence = get(Defence, Defence.apply)

  def speed: Speed = get(Speed, Speed.apply)

  override def toString: String = figureMap.map(elem => elem._1 + ": " + elem._2).foldLeft("")((res, elem) => res + " ")
}

object Status {
  def apply(elem: List[Figure]): Status = new Status(elem)
}

