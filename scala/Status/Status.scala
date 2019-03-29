package Status

import Creature.Identifilable

class Status(elem: List[Figure]) {
  val map = (Utility.lst ::: elem.toList).foldLeft(Map[String, Figure]())((res, elem) => res + (Utility.identificationString(elem) -> elem))

  private def get[T <: Figure](identifilable: Identifilable, apply: Figure => T): T = apply(map(identifilable.identificationString))

  def hp: HP = get[HP](HP, HP.apply)

  def atk: Attack = get[Attack](Attack, Attack.apply)

  def defe: Defend = get[Defend](Defend, Defend.apply)

  def speed: Speed = get[Speed](Speed, Speed.apply)

  override def toString: String = map("hp").toString
}

object Status {
  def apply(elem: List[Figure]): Status = new Status(elem)
}

