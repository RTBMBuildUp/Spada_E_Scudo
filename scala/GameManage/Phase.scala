package GameManage

import Creature.Creature
import Status.{HP, Status}

trait Phase {
  def start(player: Commander, map: Map[String, Creature]): Map[String, Creature]
  def end(map: Map[String, Creature]): Map[String, Creature]
}

case object CombatPhase extends Phase {
  override def start(commander: Commander, map: Map[String, Creature]): Map[String, Creature] = {
    def command(creature: Creature): Map[String, Creature] = {
      def declear(): Action = {
        println("you can choose attack or defence")
        readLine() match {
          case message if message == Choices.Attack.identificationString => Choices.Attack
          case message if message == Choices.Defend.identificationString => Choices.Defend
          case _ => declear()
        }
      }

      commander.command(creature, declear(), map)
    }

    map.foldLeft(Map[String, Creature]())((res, elem) => res ++ command(elem._2))
  }

  override def end(map: Map[String, Creature]): Map[String, Creature] = ???
}
