package GameManage.FlowManage.Action

import Creature.{Attackable, Creature, CreatureUtility}
import Effector.{Effectors, Spell}
import Identifilable.Identifilable

import scala.collection.immutable.Map

trait Action extends Identifilable

case object Attack extends Action with Identifilable {
  def execute(attackerName: String, targetName: String, participantMap: Map[String, Creature]): Map[String, Creature] = {
    val attacker: Attackable = participantMap(attackerName)
    val target: Creature = participantMap(targetName)

    println(attackerName + "の攻撃。")
    participantMap + CreatureUtility.creatureToMapElem(target.damage(attacker))
  }

  override def identify: String = "attack"
}

case object Defend extends Action with Identifilable {
  def execute(defenderName: String, participantMap: Map[String, Creature]): Map[String, Creature] = {
    val defender = participantMap(defenderName)

    println(defenderName + "は防御した。")
    participantMap + CreatureUtility.creatureToMapElem(defender.addEffect(Effectors.Defend()))
  }

  override def identify: String = "defend"
}

case object Chant extends Action with Identifilable {
  def execute(wizardName: String, spell: Spell, targetName: String, participantMap: Map[String, Creature]): Map[String, Creature] = {
    val wizard = participantMap(wizardName)
    val target = participantMap(targetName)

    println(wizardName + "は" + targetName + "に" + spell.identify + "を唱えた")
    println(target + "の" + spell.startMessage + "した")
    participantMap ++ wizard.chant(spell, target, participantMap)
  }

  override def identify: String = "chant"
}


