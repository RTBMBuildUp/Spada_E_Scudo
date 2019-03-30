package Status

import Creature.Identifilable
import Utility.StatusUtility

class Status(lst: List[Figure]) {
  val map: Map[String, Figure] = (StatusUtility.lst ::: lst).foldLeft(Map[String, Figure]())((res, elem) => res + (StatusUtility.identificationString(elem) -> elem))

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

