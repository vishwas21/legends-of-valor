import java.io.IOException;

public class BattleDriver {

    public static boolean chancesOfDodge(Integer value) {
        return ((Utils.randomNumber.nextInt(value) * 2) % 7) == 0;
    }

    public static void attackOfMonster(ValorMonster monster, ValorHero hero) throws IOException {
        if (!chancesOfDodge(hero.getAgility())) {
            int heroHP = hero.getCurrentHitPoints();
            int attackDamage = 0;
            if (hero.isItemEquipped(ItemType.ARMOR)) {
                attackDamage = (int) ((monster.getBaseDamage() * 0.02) - ((Armor) (hero.getItemEquipped(ItemType.ARMOR))).getDamageReduction() * 0.006);
            } else {
                attackDamage = (int) (monster.getBaseDamage() * 0.02);
            }

            System.out.println("" + monster.getName() + " successfully attacked " + hero.getName() + " for " + attackDamage + "HP!");

            if (hero.getCurrentHitPoints() <= attackDamage) {
                System.out.println("That was brutal!! Unfortunately " + hero.getName() + " has fainted! :(");
                hero.setCurrentHitPoints(0);
                hero.setFaint(true);
            } else {
                hero.setCurrentHitPoints(hero.getCurrentHitPoints() - attackDamage);
            }
        } else {
            System.out.println("" + hero.getName() + " dodged the attack!!");
        }
    }

    public static Integer checkBattleStatus(Team teamHero, Team teamMonster) {
        int returnFlag = 1;
        for (Pawn hero : teamHero.getPawnList()) {
            if (!((ValorHero) hero).getDidFaint()) {
                returnFlag = -1;
            } else {
                // remove hero from cell
                ValorCell cell = (ValorCell) ((ValorHero) hero).getCurrentCell();
                cell.removeHero();
                // put hero back in the nexus
                ((ValorHero) hero).setCurrentCell(((ValorHero) hero).getNexusCell());
            }
        }

        if (returnFlag == 1) {
            // All heroes have fainted
            return returnFlag;
        }
//
//        for (Pawn monster : teamMonster.getPawnList()) {
//            // remove fainted monsters from cell and the team
//            if (((ValorMonster) monster).getDidFaint()) {
//                ValorCell cell = (ValorCell) ((ValorMonster) monster).getCurrentCell();
//                cell.removeMonster();
//                teamMonster.removePawn(monster);
//            }
//        }
        return 2;
    }
}
