import BS.Game
import Character.Person
import Equipment.Equipment
import Status._

object Main {
  def main(args: Array[String]): Unit = {

    val arusu = Person("arusu", Status(HP(10), Attack(3), Defend(1), Speed(3)), Equipment())
    val kifa = Person("kifa", Status(HP(10), Attack(4), Defend(2), Speed(1)), Equipment())

    Game.start(List(arusu, kifa))
  }
}
