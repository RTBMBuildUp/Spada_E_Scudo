package Creature

object CreatureUtility {
  def creatureToMapElem(creature: Creature): (String, Creature) = creature.name -> creature
}
