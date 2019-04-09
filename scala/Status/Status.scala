package Status

import Utility.StatusUtility

class Status(_intMap: Map[String, Int]) {
  val intMap: Map[String, Int] = _intMap
//    StatusUtility.lst.zipAll(lst.slice(0, StatusUtility.lst.size), None, 0).foldLeft(Map[String, Int]())((res, elem) => res + (elem._1.identify -> elem._2))

  private def get(key: Identifilable): Int = intMap(key.identify)

  def hp: Int = get(HP)

  def attack: Int = get(Attack)

  def defence: Int = get(Defence)

  def speed: Int = get(Speed)

  override def toString: String = intMap.map(elem => elem._1 + ": " + elem._2).foldLeft("")((res, elem) => res + " ")
}

object Status {
  def apply(lst: List[Int]): Status =
    new Status(StatusUtility.lst.zipAll(lst.slice(0, StatusUtility.lst.size), None, 0).foldLeft(Map[String, Int]())((res, elem) => res + (elem._1.identify -> elem._2)))

  def apply(identifilableMap: Map[Identifilable, Int]): Status = new Status(identifilableMap.map(tuple => tuple._1.identify -> tuple._2))

}

