package GameManage

import Character.Character
import Queue.Queue
import Status.{Figure, HP, Utility}


object Game {
  def start(characterList: List[Character]): Unit = {
    def play(characterMap: Map[String, Character], turnQueue: Queue[Character]): Unit = {
      val characterList = characterMap.toList.foldLeft(List[Character]())((res, elem) => elem._2 :: res)

      characterList.filter(!_.isAlive) match {
        case deadManLst if deadManLst.isEmpty =>
          val turnCharacter = turnQueue.head
          val defender = characterList.filter(_ != turnCharacter).head

          println(defender.flucstrateStatus(HP, (hp: Figure) => HP(hp - turnCharacter.attack - defender.defend)).status)

          play(
            characterMap + (
                    defender.name ->
                            defender.flucstrateStatus(
                              HP,
                              (hp: Figure) => HP(hp - (turnCharacter.attack - defender.defend))
                            )
                    ),
            turnQueue.tail enqueue turnCharacter
          )

        case deadManLst => deadManLst.foreach(deadMan => println(deadMan + " is Dead"))
      }
    }

    val characterMap = characterList.foldLeft(Map[String, Character]())((res, elem) => res + (elem.name -> elem))
    val turnQueue = Queue(characterList.sortWith((l, r) => l.status.speed < r.status.speed))

    play(characterMap, turnQueue)
  }
}
