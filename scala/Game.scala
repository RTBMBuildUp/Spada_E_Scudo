package BS

import Status.Figure
import Character.Character

object Game {
  def start(characterList: List[Character]): Unit = {
    def play(characterMap: Map[String, Character], turnQueue: Queue[Character]): Unit = {
      def calcDamage(attacker: Character, Defender: Character): Figure = characterMap(attacker.name).attack - characterMap(Defender.name).defend

      val characterList = characterMap.toList.foldLeft(List[Character]())((res, elem) => elem._2 :: res)

      characterList.filter(!_.isAlive) match {
        case deadManLst if deadManLst.isEmpty =>
          val turnCharacter = turnQueue.head
          val defender = characterList.filter(_ != turnCharacter).head

          println(defender.reduceHP(calcDamage(turnCharacter, defender)))
          play(
            characterMap + (defender.name -> defender.reduceHP(calcDamage(turnCharacter, defender))),
            turnQueue.tail enqueue turnCharacter
          )
        case deadManLst => deadManLst.foreach(deadMan => println(deadMan + " is Dead"))
      }
    }

    val characterMap = characterList.foldLeft(Map[String, Character]())((res, elem) => res + (elem.name -> elem))
    val turnQueue = Queue[Character](characterList.sortWith((l, r) => l.status.speed < r.status.speed))

    play(characterMap, turnQueue)
  }
}
