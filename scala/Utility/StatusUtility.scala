package Utility

import GameManage.Choices
import Status._

object StatusUtility {
  def lst: List[Figure] = List[Figure](HP(0), Attack(0), Defence(0), Speed(0))

  def identificationString(figure: Figure): String = (figure match {
    case _: HP => HP
    case _: Attack => Attack
    case _: Defence => Defence
    case _: Speed => Speed
  }).identificationString



  def identificationString(apply: Figure => Figure): String = (apply match {
    case _: Function1[_, HP] => HP
    case _: Function1[_, Attack] => Attack
    case _: Function1[_, Defence] => Defence
    case _: Function1[_, Speed] => Speed
  }).identificationString
}
