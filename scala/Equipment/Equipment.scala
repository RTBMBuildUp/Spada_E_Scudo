package Equipment

abstract class Equipment(_weapon: Weapon, _armor: Armor) {
  def weapon: Weapon = _weapon
  def armor: Armor = _armor
}

object Equipment {
  def apply(_weapon: Weapon = EmptyHand, _armor: Armor = Nakedness): Equipment = new ImplEquipment(_weapon, _armor)
  def apply(): Equipment = new Unarmed

  private class ImplEquipment(_weapon: Weapon, _armor: Armor) extends Equipment(_weapon, _armor)
  private class Unarmed extends Equipment(EmptyHand, Nakedness)
}

