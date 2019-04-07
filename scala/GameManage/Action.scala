package GameManage

import Creature.Creature
import _root_.Creature.Job.Wizard
import Effector.EffectorLst
import Status.{Defence, Figure, Identifilable, Speed}
import Utility._

import scala.collection.immutable.Map

trait Action {
  def execute(creature: Creature, map: Map[String, Creature]): Map[String, Creature]
}

object Choices {
  case object Attack extends Action with Identifilable {
    override def execute(creature: Creature, participantMap: Map[String, Creature]): Map[String, Creature] = {
      println(creature + ": 攻撃する相手を宣言。")
      val key = readLine()

      participantMap.toList.filter(_._1 != creature.name).filter(_._1 == key) match {
        case Nil => execute(creature, participantMap)
        case (_, target) :: _ =>
          println(creature + "の攻撃。")
          println(target + "は" + (creature.attack - target.defend) + "のダメージを受けた。")
          participantMap + CreatureUtility.creatureToMapElem(target.damage(creature))
      }
    }

    override def identificationString: String = "attack"
  }

  case object Defend extends Action with Identifilable {
    override def execute(creature: Creature, participantMap: Map[String, Creature]): Map[String, Creature] = {
      println(creature + "は防御した。")
      participantMap + CreatureUtility.creatureToMapElem(creature.addEffect(EffectorLst.Defend))
    }

    override def identificationString: String = "defend"
  }

  case object Chant extends Action with Identifilable {
    override def execute(creature: Creature, participantMap: Map[String, Creature]): Map[String, Creature] = {
      println(creature + ": 呪文の選択。")
      val spellName = readLine()

      creature.spellLst.filter(spell => spell.identificationString == spellName) match {
        case Nil => execute(creature, participantMap)
        case spell :: tail => participantMap ++ creature.chant(spell, participantMap)
      }
    }

    override def identificationString: String = "Chant"
  }

}
