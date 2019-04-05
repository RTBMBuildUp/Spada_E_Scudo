package Status

import Utility.StatusUtility

class Status(lst: List[Figure]) {
  val figureMap: Map[String, Figure] =
    (StatusUtility.lst ::: lst).foldLeft(Map[String, Figure]())((res, elem) => res + (StatusUtility.identificationString(elem) -> elem))

  private def get[T <: Figure](identifilable: Identifilable, apply: Figure => T): T = apply(figureMap(identifilable.identificationString))

  def hp: HP = get(HP, HP.apply)

  def atk: Attack = get(Attack, Attack.apply)

  def defe: Defence = get(Defence, Defence.apply)

  def speed: Speed = get(Speed, Speed.apply)

  override def toString: String = figureMap("hp").toString
}

object Status {
  def apply(elem: List[Figure]): Status = new Status(elem)
}

