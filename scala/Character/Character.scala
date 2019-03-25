package Character

import Status.{Figure, _}

abstract class Character(_name: String, _status: Status) extends Attackable with Defendable {
  def name: String = _name

  def status: Status = _status

  def isAlive: Boolean = HP(0) != status.hp

  def flucstrateStatus(flucstrateFunc: Status => Status): Character = Character(_name, flucstrateFunc(status))

  def reduceHP(receivedForce: Figure): Character = {
    flucstrateStatus((stat: Status) => Status((HP((status.hp - receivedForce).figure) :: stat.map.unzip._2.toList.reverse).reverse))
//    Character(_name, Status(HP((status.hp - receivedForce).figure)))
  }

}

object Character {
  def apply(_name: String, _status: Status): Character = new ImplCharacter(_name, _status)

  private class ImplCharacter(_name: String, _status: Status) extends Character(_name: String, _status) {
    def attack: Attack = ???
    def defend: Defend = ???
  }
}
