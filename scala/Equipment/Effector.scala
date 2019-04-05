package Equipment

trait Effector[T] {
  def activate: T => T
}
