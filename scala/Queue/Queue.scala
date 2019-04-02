package Queue

class Queue[T] private (
                               private var leading: List[T],
                               private var trailing: List[T]
                       ) {
  private def miror(): Unit = if (leading.isEmpty) {
    leading = trailing.reverse
    trailing = Nil
  }

  def head: T = {
    miror()
    leading.head
  }

  def tail: Queue[T] = {
    miror()
    new Queue(leading.tail, trailing)
  }

  def enqueue(elem: T): Queue[T] = new Queue(leading, elem :: trailing)

  def toList: List[T] = leading ::: trailing

  def isEmpty: Boolean = (leading ::: trailing).isEmpty
}

object Queue {
  def apply[T](elem: T*): Queue[T] = new Queue(elem.toList, Nil)

  def apply[T](lst: List[T]): Queue[T] = new Queue(lst, Nil)
}
