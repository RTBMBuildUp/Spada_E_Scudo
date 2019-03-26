package Character

import Status.{Attack, Defend, Figure, Status}

class Monster(_name: String, _status: Status) extends Character(_name, _status) {
  def attack: Attack = Attack(status.atk.figure)

  def defend: Defend = Defend(status.defe.figure)

  override def flucstrateStatus(identifilable: Identifilable, func: Figure => Figure): Character =
    Monster(name, super.flucstrateStatus(identifilable, func).status)

}


object Monster {
  def apply(name: String, status: Status): Monster = new Monster(name, status)
}