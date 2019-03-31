package GameManage

import Creature.Creature
import Status.{Figure, Identifilable}
import Utility._

trait Action {
  def activated[T <: Creature](creature: T, map: Map[String, Creature]): Map[String, Creature]
}

object Order {
  case object Attack extends Action with Identifilable {
    override def activated[T <: Creature](creature: T, map: Map[String, Creature]): Map[String, Creature] =
      map + CreatureUtility.creatureToMapElem(map(readLine()).damage(creature))

    override def identificationString: String = "attack"
  }

  case object Defend extends Action with Identifilable {
    override def activated[T <: Creature](creature: T, map: Map[String, Creature]): Map[String, Creature] =
      map + CreatureUtility.creatureToMapElem(creature.flucstrateStatus(Status.Defend, (defend: Figure) => defend + 2))

    override def identificationString: String = "defend"
  }

}
