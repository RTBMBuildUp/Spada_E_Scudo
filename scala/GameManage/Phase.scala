package GameManage

import Creature.Creature
import Status.{HP, Status}

trait Phase {
  def start(player: Player, map: Map[String, Creature]): Map[String, Creature]
  def end(map: Map[String, Creature]): Map[String, Creature]
}

case object CombatPhase extends Phase {
  override def start(player: Player, map: Map[String, Creature]): Map[String, Creature] = {
    def order(creature: Creature): Map[String, Creature] = {
      def declear(): Action = readLine() match {
        case Order.Attack.identificationString => Order.Attack
        case Order.Defend.identificationString => Order.Defend
      }

      println("you can choose attack or defence")
      player.order(creature, declear(), map)
    }

    val creatureLst = map.unzip._2.toList

    map.foldLeft(Map[String, Creature]())((res, elem) => res ++ order(elem._2))
  }

  override def end(map: Map[String, Creature]): Map[String, Creature] = ???
}
