import Creature.Job.Wizard
import Creature.Person
import Effector.Effectors
import Effector.Equipment.Equipment
import GameManage.Game
import Identifilable._
import Status._

object Main {
  def main(args: Array[String]): Unit = {
    val arusu = Person("arusu", Status(Map[Identifilable, Int](HP -> 10, Attack -> 3, Defence -> 1, Speed -> 3)), Equipment())
    val kifa = Person("kifa", Status(Map[Identifilable, Int](HP -> 10, Attack -> 4, Defence -> 2, Speed -> 1)), Equipment())
    val maribel = Wizard("maribel", Status(Map[Identifilable, Int](HP -> 10, Attack -> 1, Defence -> 1, Speed -> 2)), Equipment(), List(Effectors.TwinHits()))

    Game.start(List(arusu, kifa, maribel))
  }
}
