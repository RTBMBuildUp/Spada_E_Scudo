package Status

abstract class Status(_hp: HP, _atk: Attack, _def: Defend, _speed: Speed, _chargeTime: ChargeTime = ChargeTime(0)) {
  def hp: HP = _hp

  def atk: Attack = _atk

  def defe: Defend = _def

  def speed: Speed = _speed

  def chargeTime: ChargeTime = _chargeTime
}

object Status {
  def apply(_hp: HP, _atk: Attack, _def: Defend, _speed: Speed, _chargeTime: ChargeTime = ChargeTime(0)): Status = new ImplStatus(_hp, _atk, _def, _speed, _chargeTime)

  private class ImplStatus(_hp: HP, _atk: Attack, _def: Defend, _speed: Speed, _chargeTime: ChargeTime = ChargeTime(0)) extends Status(_hp, _atk, _def, _speed, _chargeTime)
}

