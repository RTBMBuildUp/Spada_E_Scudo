package GameManage.FlowManage.Action

import Creature.{Attackable, Creature, CreatureUtility}
import Effector.{Effectors, Spell}
import GameManage.ParticipantMap.ParticipantMap
import Identifilable.Identifilable

trait Action extends Identifilable

case object Attack extends Action {
  def execute(attackerName: String, targetName: String, participantMap: ParticipantMap): ParticipantMap = {
    val attacker: Attackable = participantMap(attackerName)
    val target: Creature = participantMap(targetName)

    println(attackerName + "の攻撃。")
    participantMap + CreatureUtility.creatureToMapElem(target.damage(attacker))
  }

  override def identify: String = "attack"
}

case object Defend extends Action {
  def execute(defenderName: String, participantMap: ParticipantMap): ParticipantMap = {
    val defender = participantMap(defenderName)

    println(defenderName + "は防御した。")
    participantMap + CreatureUtility.creatureToMapElem(defender.applyEffect(Effectors.Defend))
  }

  override def identify: String = "defend"
}

case object Chant extends Action {
  def execute(wizardName: String, spell: Spell, targetName: String, participantMap: ParticipantMap): ParticipantMap = {
    val wizard = participantMap(wizardName)
    val target = participantMap(targetName)

    println(wizardName + "は" + targetName + "に" + spell.identify + "を唱えた")
    participantMap ++ wizard.chant(spell, target, participantMap)
  }

  override def identify: String = "chant"
}


