package Action

import Creature.{Creature, CreatureUtility}
import Effector.{EffectorLst, Spell}
import Identifilable.Identifilable

import scala.collection.immutable.Map

object Choices {
  case object Attack extends Action with Identifilable {
    override def execute(creature: Creature, participantMap: Map[String, Creature]): Map[String, Creature] = {
      def declearTarget(readFunc: () => String = readLine): String = {
        println(creature + ": 対象を宣言。")
        readFunc()
      }

      def target(key: String = declearTarget()): Creature =
        participantMap.toList.filter(participant => participant._1 == key && key != creature.name) match {
          case Nil => target()
          case (_, target) :: _ => target
        }

      println(creature + "の攻撃。")
      participantMap + CreatureUtility.creatureToMapElem(target().damage(creature))
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
      def target(key: String = declearTarget()): Creature =
        participantMap.toList.filter(participant => participant._1 == key && key != creature.name) match {
          case Nil => target()
          case (_, target) :: _ => target
        }

      def declearTarget(readFunc: () => String = readLine): String = {
        println(creature + ": 対象を宣言。")
        readFunc()
      }

      def declearSpell(readFunc: () => String = readLine): String = {
        println(creature + ": 呪文の宣言。")
        readFunc()
      }

      def spell(spellName: String = declearSpell()): Spell = creature.spellLst.filter(_.identify == spellName) match {
        case Nil => spell()
        case spell :: tail => spell
      }

      participantMap ++ creature.chant(spell(), participantMap(declearTarget()), participantMap)
    }

    override def identify: String = "chant"
  }

}
