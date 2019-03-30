package Utility

import Status._

object StatusUtility {
  def lst: List[Figure] = List[Figure](HP(0), Attack(0), Defend(0), Speed(0))

  def identificationString(figure: Figure): String = (figure match {
    case _: HP => HP
    case _: Attack => Attack
    case _: Defend => Defend
    case _: Speed => Speed
  }).identificationString
}
