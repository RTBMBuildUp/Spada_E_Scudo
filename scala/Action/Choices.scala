package Action

import Creature.{Creature, CreatureUtility}
import Effector.EffectorLst
import Identifilable.Identifilable

import scala.collection.immutable.Map

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

    override def identify: String = "attack"
  }

  case object Defend extends Action with Identifilable {
    override def execute(creature: Creature, participantMap: Map[String, Creature]): Map[String, Creature] = {
      println(creature + "は防御した。")
      participantMap + CreatureUtility.creatureToMapElem(creature.addEffect(EffectorLst.Defend()))
    }

    override def identify: String = "defend"
  }

  case object Chant extends Action with Identifilable {
    override def execute(creature: Creature, participantMap: Map[String, Creature]): Map[String, Creature] = {
      println(creature + ": 呪文の選択。")
      val spellName = readLine()

      creature.spellLst.filter(spell => spell.identify == spellName) match {
        case Nil => execute(creature, participantMap)
        case spell :: tail => participantMap ++ creature.chant(spell, participantMap(readLine()), participantMap)
      }
    }

    override def identify: String = "chant"
  }

}
