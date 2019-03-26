package Status

import Character.Identifilable

class Status(elem: List[Figure]) {
  val map = (Utility.lst ::: elem.toList).foldLeft(Map[String, Figure]())((res, elem) => res + (Utility.identificationString(elem) -> elem))

  private def get[T <: Figure](identifilable: Identifilable, apply: Int => T): T = apply(map(identifilable.identificationString).figure)

  def hp: HP = get[HP](HP, HP.apply)

  def atk: Attack = get[Attack](Attack, Attack.apply)

  def defe: Defend = get[Defend](Defend, Defend.apply)

  def speed: Speed = get[Speed](Speed, Speed.apply)

  def chargeTime: ChargeTime = get[ChargeTime](ChargeTime, ChargeTime.apply)

  override def toString: String = map("hp").toString
}

object Status {
  def apply(elem: List[Figure]): Status = new Status(elem)
}

