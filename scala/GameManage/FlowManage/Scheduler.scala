package GameManage.FlowManage

import GameManage.FlowManage.Phase.Phase
import GameManage.ParticipantMap.ParticipantMap

import scala.collection.immutable.Queue

class Scheduler(
                       _participantMap: ParticipantMap,
                       _currentPhase: Phase,
                       _actionQueue: Queue[(String, ParticipantMap => ParticipantMap)]
               ) {
  def participantMap: ParticipantMap = _participantMap
  def actionQueue: Queue[(String, ParticipantMap => ParticipantMap)] = _actionQueue
  def goAhead(): Scheduler = this._currentPhase.start(this)
}

object Scheduler {
  def apply(
                   participantMap: ParticipantMap,
                   currentPhase: Phase,
                   actionQueue: Queue[(String, ParticipantMap => ParticipantMap)] = Queue(Nil: _*)
           ): Scheduler = new Scheduler(participantMap, currentPhase, actionQueue)
}
