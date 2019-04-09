package GameManage.FlowManage

import Action.Action
import Creature.Creature
import GameManage.Player.Commander
import Queue.Queue

class Scheduler(_participantMap: Map[String, Creature], _currentPhase: Phase, _actionQueue: Queue[(String, Action)]) {
  def participantMap: Map[String, Creature] = _participantMap
  def actionQueue: Queue[(String, Action)] = _actionQueue
  def goAhead(commander: Commander): Scheduler = this._currentPhase.start(commander, this)
}

object Scheduler {
  def apply(participantMap: Map[String, Creature], currentPhase: Phase, actionQueue: Queue[(String, Action)] = Queue(Nil)): Scheduler =
    new Scheduler(participantMap, currentPhase, actionQueue)
}
