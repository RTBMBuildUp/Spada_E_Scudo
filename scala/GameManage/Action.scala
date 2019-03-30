package GameManage

import Creature.Creature
import Status.Figure
import Utility._

trait Action {
  def activated[T <: Creature](creature: T, map: Map[String, Creature]): Map[String, Creature]
}

object Order {
  case object Attack extends Action {
    override def activated[T <: Creature](creature: T, map: Map[String, Creature]): Map[String, Creature] =
      map + CreatureUtility.creatureToMapElem(map(readLine()).damage(creature))
  }

  case object Defend extends Action {
    override def activated[T <: Creature](creature: T, map: Map[String, Creature]): Map[String, Creature] =
      map + CreatureUtility.creatureToMapElem(creature.flucstrateStatus(Status.Defend, (defend: Figure) => defend + 2))
  }

}
