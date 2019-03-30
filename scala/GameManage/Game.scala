package GameManage

import Creature.{Creature, Monster}
import Queue.Queue
import Status.{Figure, HP, Status}


object Game {
  def start(creatureList: List[Creature]): Unit = {
    def play(creatureMap: Map[String, Creature], turnQueue: Queue[Creature]): Unit = {
      val creatureList = creatureMap.toList.foldLeft(List[Creature]())((res, elem) => elem._2 :: res)

      creatureList.filter(!_.isAlive) match {
        case deadManLst if deadManLst.isEmpty =>
          val turnCreature = turnQueue.head
          val defender = creatureList.filter(_ != turnCreature).head

          println(defender.flucstrateStatus(HP, (hp: Figure) => HP(hp - turnCreature.attack - defender.defend)).status)

          play(
            creatureMap + (
                    defender.name ->
                            defender.flucstrateStatus(
                              HP,
                              (hp: Figure) => HP(hp - (turnCreature.attack - defender.defend))
                            )
                    ),
            turnQueue.tail enqueue turnCreature
          )

        case deadManLst => deadManLst.foreach(deadMan => println(deadMan + " is Dead"))
      }
    }

    val creatureMap = creatureList.foldLeft(Map[String, Creature]())((res, elem) => res + (elem.name -> elem))
    val turnQueue = Queue(creatureList.sortWith((l, r) => l.status.speed < r.status.speed))

    play(creatureMap, turnQueue)
  }
}
