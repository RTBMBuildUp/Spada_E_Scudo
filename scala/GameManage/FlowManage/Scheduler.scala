package GameManage.FlowManage

import Creature.Creature
import GameManage.FlowManage.Phase.Phase

import scala.collection.immutable.Queue

class Scheduler(_participantMap: Map[String, Creature], _currentPhase: Phase, _actionQueue: Queue[Map[String, Creature] => Map[String, Creature]]) {
  def participantMap: Map[String, Creature] = _participantMap
  def actionQueue: Queue[Map[String, Creature] => Map[String, Creature]] = _actionQueue
  def goAhead(): Scheduler = this._currentPhase.start(this)
}

object Scheduler {
  def apply(participantMap: Map[String, Creature], currentPhase: Phase, actionQueue: Queue[Map[String, Creature] => Map[String, Creature]] = Queue(Nil: _*)): Scheduler =
    new Scheduler(participantMap, currentPhase, actionQueue)
}
