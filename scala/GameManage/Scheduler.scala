package GameManage

import Creature.Creature
import Queue.Queue
import Utility.CreatureUtility

class Scheduler(_participantMap: Map[String, Creature], _currentPhase: Phase, _actionQueue: Queue[(Creature, Action)]) {
  def participantMap: Map[String, Creature] = _participantMap
  def actionQueue: Queue[(Creature, Action)] = _actionQueue
  def goAhead(commander: Commander): Scheduler = this._currentPhase.start(commander, this)
}

object Scheduler {
  def apply(participantMap: Map[String, Creature], currentPhase: Phase, actionQueue: Queue[(Creature, Action)] = Queue(Nil)): Scheduler =
    new Scheduler(participantMap, currentPhase, actionQueue)
}
