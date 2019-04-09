package Utility

import Status._

object StatusUtility {
  def lst: List[Figure] = List[Figure](HP(0), Attack(0), Defence(0), Speed(0))

  def identificationString(figure: Figure): String = (figure match {
    case _: HP => HP
    case _: Attack => Attack
    case _: Defence => Defence
    case _: Speed => Speed
  }).identificationString
}
