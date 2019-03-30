package GameManage

import Creature.{Creature, Monster}
import Queue.Queue
import Status.{Figure, HP, Status}
import Utility.CreatureUtility


object Game {
  def start(creatureList: List[Creature]): Unit = {
    def play(creatureMap: Map[String, Creature], turnQueue: Queue[Creature]): Unit = {
      val creatureList = creatureMap.toList.foldLeft(List[Creature]())((res, elem) => elem._2 :: res)
      val res = Player.apply.order(turnQueue.head, Order.Attack, creatureMap)

      println(res)

      creatureList.filter(!_.isAlive) match {
        case deadManLst if deadManLst.isEmpty =>
          play(
            res,
            turnQueue.tail enqueue turnQueue.head
          )

        case deadManLst => deadManLst.foreach(deadMan => println(deadMan + " is Dead"))
      }
    }

    val creatureMap = creatureList.foldLeft(Map[String, Creature]())((res, elem) => res + (elem.name -> elem))
    val turnQueue = Queue(creatureList.sortWith((l, r) => l.status.speed < r.status.speed))

    play(creatureMap, turnQueue)
  }
}
