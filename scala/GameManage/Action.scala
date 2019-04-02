package GameManage

import Creature.Creature
import Status.{Figure, Identifilable}
import Utility._

trait Action {
  def activate[T <: Creature](creature: T, map: Map[String, Creature]): Map[String, Creature]
}

object Choices {
  case object Attack extends Action with Identifilable {
    override def activate[T <: Creature](creature: T, participantMap: Map[String, Creature]): Map[String, Creature] = {
      println("choose a target")
      readLine() match {
        case key if participantMap.filter(_._1 != creature.name).exists(_._1 == key) => participantMap + CreatureUtility.creatureToMapElem(participantMap(key).damage(creature))
        case _ => activate(creature, participantMap)
      }
    }

    override def identificationString: String = "attack"
  }

  case object Defend extends Action with Identifilable {
    override def activate[T <: Creature](creature: T, map: Map[String, Creature]): Map[String, Creature] =
      map + CreatureUtility.creatureToMapElem(creature.flucstrateStatus(Status.Defend, (defend: Figure) => defend + 2))

    override def identificationString: String = "defend"
  }

}
