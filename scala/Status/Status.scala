package Status

abstract class Status(_hp: HP, _atk: Attack, _def: Defend, _speed: Speed) {
  def hp: HP = _hp

  def atk: Attack = _atk

  def defe: Defend = _def

  def speed: Speed = _speed
}

object Status {
  def apply(_hp: HP, _atk: Attack, _def: Defend, _speed: Speed): Status = new ImplStatus(_hp, _atk, _def, _speed)

  private class ImplStatus(_hp: HP, _atk: Attack, _def: Defend, _speed: Speed) extends Status(_hp, _atk, _def, _speed)
}

