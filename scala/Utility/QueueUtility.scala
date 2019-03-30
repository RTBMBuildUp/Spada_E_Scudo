package Utility

import Queue.Queue

object QueueUtility {
  def queueToList[T](queue: Queue[T], lst: List[T] = Nil): List[T] = queue.tail match {
    case remain if remain.isEmpty => lst
    case _ => queue.head :: lst
  }
}
