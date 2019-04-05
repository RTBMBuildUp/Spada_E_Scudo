package GameManage

import Creature.Creature
import Status.{Defence, Figure, Identifilable, Speed}
import Utility._

trait Action {
  def activate[T <: Creature](creature: T, map: Map[String, Creature]): Map[String, Creature]
}

object Choices {
  case object Attack extends Action with Identifilable {
    override def activate[T <: Creature](creature: T, participantMap: Map[String, Creature]): Map[String, Creature] = {
      println(creature.name + ": 攻撃する相手を宣言。")
      readLine() match {
        case key if participantMap.filter(_._1 != creature.name).exists(_._1 == key) =>
          println(creature + "の攻撃。")
          println(participantMap(key) + "は" + (creature.attack - participantMap(key).defend) + "のダメージを受けた。")
          participantMap.toList.unzip._2.foreach(creature => println(creature.defend))
          participantMap + CreatureUtility.creatureToMapElem(participantMap(key).damage(creature))
        case _ => activate(creature, participantMap)
      }
    }

    override def identificationString: String = "attack"
  }

  case object Defend extends Action with Identifilable {
    override def activate[T <: Creature](creature: T, participantMap: Map[String, Creature]): Map[String, Creature] = {
      println(creature + "は防御した。")
      participantMap + CreatureUtility.creatureToMapElem(creature.addEffect((defence: Figure) => Defence(defence + 2)))
    }

    override def identificationString: String = "defend"
  }

}
