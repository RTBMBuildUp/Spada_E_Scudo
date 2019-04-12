package Choice

import Creature.Creature

trait Choice {
  def declareTarget(executerName: String, participantMap: Map[String, Creature], readFunc: () => String): Map[String, Creature] => Map[String, Creature]
}
