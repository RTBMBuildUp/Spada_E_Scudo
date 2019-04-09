package Action

import Creature.{Creature, CreatureUtility}
import Effector.EffectorLst
import Identifilable.Identifilable

import scala.collection.immutable.Map

trait Action {
  def execute(creature: Creature, map: Map[String, Creature]): Map[String, Creature]
}
