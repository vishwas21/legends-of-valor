/**
 * NPCFactory Class: Design Pattern class used to produce Heroes and Monsters in the game.
 */
public class NPCFactory {
    public ValorNPC createObject(NPCType npcType) throws IllegalStateException {
        if (npcType == NPCType.HERO) {
            return new ValorHero();
        } else if (npcType == NPCType.MONSTER) {
            return new ValorMonster();
        } else {
            throw new IllegalStateException("Invalid value passed to the NPC Factory!");
        }
    }
}
