import Creature.Job.Wizard
import Creature.{Creature, Monster, Person}
import Effector.Effectors
import Effector.Equipment.Equipment
import GameManage.Game
import Identifilable._
import Status._

object Main {
  def main(args: Array[String]): Unit = {
    val arusu = Person("arusu", Status(Map[Identifilable, Int](HP -> 10, Attack -> 3, Defence -> 1, Speed -> 1)), Equipment())

    val kifa = Person("kifa", Status(Map[Identifilable, Int](HP -> 10, Attack -> 4, Defence -> 2, Speed -> 1)), Equipment())

    val maribel = Wizard(
      "maribel",
      Status(Map[Identifilable, Int](HP -> 10, Attack -> 1, Defence -> 1, Speed -> 4)),
      Equipment(),
      List(Effectors.Oomph(), Effectors.Frizz, Effectors.Acceleratle())
    )

    val melvin = Wizard(
      "melvin",
      Status(Map[Identifilable, Int](HP -> 10, Attack -> 1, Defence -> 1, Speed -> 2)),
      Equipment(),
      List(Effectors.Zing)
    )

    val slime = Monster(
      "slimeA",
      Status(Map[Identifilable, Int](HP -> 10, Attack -> 3, Defence -> 1, Speed -> 3))
    )

    val enemyLst: List[Creature] = List(slime)
    val allyLst: List[Creature] = List(arusu, kifa, maribel, melvin)

    Game.start(List(arusu, kifa, maribel, melvin))
  }
}
