package Character

import Status.{Figure, _}

abstract class Character(_name: String, _status: Status) extends Attackable with Defendable {
  def name: String = _name

  def status: Status = _status

  def isAlive: Boolean = HP(0) != status.hp

  def flucstrateStatus(flucstrateFunc: Figure => Figure): Character = {
    val statusLst =
      List[Figure](status.hp, status.atk, status.defe, status.speed, status.chargeTime)

    val statusMap: Map[String, Figure] =
            statusLst.foldLeft(Map[String, Figure]())((res, elem) => res + (elem.identificationString -> elem))

    val figure = flucstrateFunc(statusLst.filter(elem => elem.identificationString == flucstrateFunc().identificationString).head)

    statusMap + (figure.identificationString -> figure)

    Character(
      _name,
      status
    )
  }

  def reduceHP(receivedForce: Figure): Character = Character(_name, Status(HP((status.hp - receivedForce).figure), status.atk, status.defe, status.speed))
}

object Character {
  def apply(_name: String, _status: Status): Character = new ImplCharacter(_name, _status)

  private class ImplCharacter(_name: String, _status: Status) extends Character(_name: String, _status) {
    def attack: Attack = ???
    def defend: Defend = ???
  }
}
