package GameManage

import Creature.Creature
import Status.{Figure, Identifilable}
import Utility._

trait Action {
  def activated[T <: Creature](creature: T, map: Map[String, Creature]): Map[String, Creature]
}

object Choices {
  case object Attack extends Action with Identifilable {
    override def activated[T <: Creature](creature: T, map: Map[String, Creature]): Map[String, Creature] = readLine() match {
      case key if map.filter(_._1 != creature.name).exists(_._1 == key) => map + CreatureUtility.creatureToMapElem(map(key).damage(creature))
      case _ => activated(creature, map)
    }

    override def identificationString: String = "attack"
  }

  case object Defend extends Action with Identifilable {
    override def activated[T <: Creature](creature: T, map: Map[String, Creature]): Map[String, Creature] =
      map + CreatureUtility.creatureToMapElem(creature.flucstrateStatus(Status.Defend, (defend: Figure) => defend + 2))

    override def identificationString: String = "defend"
  }

}
