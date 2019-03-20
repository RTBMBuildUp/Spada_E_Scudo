package Character

import Status._

abstract class Character(_name: String, _status: Status) {
  def name: String = _name

  def status: Status = _status

  def attack: Attack

  def defend: Defend

  def isAlive: Boolean = Figure(0) < status.hp

  def reduceHP(receivedForce: Figure): Character = Character(_name, Status(HP((status.hp - receivedForce).figure), status.atk, status.defe, status.speed))
}

object Character {
  def apply(_name: String, _status: Status): Character = new ImplCharacter(_name, _status)

  private class ImplCharacter(_name: String, _status: Status) extends Character(_name: String, _status) {
    def attack: Attack = ???
    def defend: Defend = ???
  }
}
