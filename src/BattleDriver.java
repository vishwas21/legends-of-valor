/**
 * BattleDriver Class: This class has all the functions required for the battle to run, funstions to use the potions, spells and attack features as well.
 */

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BattleDriver {

    public static boolean usePotion(ValorHero hero) throws IOException {
        if (hero.getItemInventory().get(ItemType.POTION).size() != 0) {
            Utils.println(MsgType.INFO, "Here are the list of potions which your hero has : ");
            Utils.displayPotions(hero.getItemInventory().get(ItemType.POTION));
            System.out.print("\n Please choose a potion which you would like to use (Please note that they are exhaustible : )");
            int selectedPotion = Integer.parseInt(Utils.input.readLine()) - 1;

            if (selectedPotion < 0 || selectedPotion >= hero.getItemInventory().get(ItemType.POTION).size()) {
                Utils.println(MsgType.WARNING, "Please choose a valid option!!");
                return false;
            }

            Potion potion = ((Potion) (hero.getItemInventory().get(ItemType.POTION).get(selectedPotion)));

            if (potion.getPotionType() == PotionType.STRENGTH) {
                hero.setStrength(hero.getStrength() + potion.getEffectAmount());
                Utils.println(MsgType.SUCCESS, "" + hero.getName() + " used the " + potion.getName() + " increasing the hero's strength to " + hero.getStrength());
            } else if (potion.getPotionType() == PotionType.AGILITY) {
                hero.setAgility(hero.getAgility() + potion.getEffectAmount());
                Utils.println(MsgType.SUCCESS, "" + hero.getName() + " used the " + potion.getName() + " increasing the hero's agility to " + hero.getAgility());
            } else if (potion.getPotionType() == PotionType.HEALTH) {
                hero.setCurrentHitPoints(hero.getCurrentHitPoints() + potion.getEffectAmount());
                Utils.println(MsgType.SUCCESS, "" + hero.getName() + " used the " + potion.getName() + " increasing the hero's health to " + hero.getCurrentHitPoints());
            } else if (potion.getPotionType() == PotionType.MAGIC) {
                hero.setCurrentMagicPoints(hero.getCurrentMagicPoints() + potion.getEffectAmount());
                Utils.println(MsgType.SUCCESS, "" + hero.getName() + " used the " + potion.getName() + " increasing the hero's mana to " + hero.getCurrentMagicPoints());
            } else if (potion.getPotionType() == PotionType.DEXTERITY) {
                hero.setDexterity(hero.getDexterity() + potion.getEffectAmount());
                Utils.println(MsgType.SUCCESS, "" + hero.getName() + " used the " + potion.getName() + " increasing the hero's dexterity to " + hero.getDexterity());
            } else {
                hero.setStrength(hero.getStrength() + potion.getEffectAmount());
                hero.setAgility(hero.getAgility() + potion.getEffectAmount());
                hero.setCurrentHitPoints(hero.getCurrentHitPoints() + potion.getEffectAmount());
                hero.setCurrentMagicPoints(hero.getCurrentMagicPoints() + potion.getEffectAmount());
                hero.setDexterity(hero.getDexterity() + potion.getEffectAmount());
                Utils.println(MsgType.SUCCESS, "" + hero.getName() + " used the " + potion.getName() + ". Effected attributes: \nStrength: " + hero.getStrength() + "\n" +
                        "Agility: " + hero.getAgility() + "\nHealth: " + hero.getCurrentHitPoints() + "\nMana: " + hero.getCurrentMagicPoints() + "\n" +
                        "Dexterity: " + hero.getDexterity());
            }

            hero.getItemInventory().get(ItemType.POTION).remove(selectedPotion);
            return true;
        } else {
            Utils.println(MsgType.WARNING, "Uh oh! The Hero is out of potions!! Please buy some from the market to try again!!");
            return false;
        }
    }

    public static int useSpell(ValorHero hero, ValorMonster monster) throws IOException {
        // return -1 if the spell is not casted
        // return 0 if the spell is casted and the monster is not killed
        // return 1 if the spell is casted and the monster is killed
        if (hero.getItemInventory().get(ItemType.SPELL).size() != 0) {
            Utils.println(MsgType.INFO, "Here are the list of spell which your hero has : ");
            Utils.displaySpells(hero.getItemInventory().get(ItemType.SPELL));
            System.out.print("\n Please choose a spell which you would like to use (Please note that they are exhaustible : )");
            int selectedSpell = Integer.parseInt(Utils.input.readLine()) - 1;

            if (selectedSpell < 0 || selectedSpell >= hero.getItemInventory().get(ItemType.SPELL).size()) {
                Utils.println(MsgType.WARNING, "Please choose a valid option!!");
                return -1;
            }

            Spell spell = ((Spell) (hero.getItemInventory().get(ItemType.SPELL).get(selectedSpell)));

            if (spell.getMpCost() > hero.getCurrentMagicPoints()) {
                Utils.println(MsgType.WARNING, "Uh oh! The Hero does not have enough mana to cast the spell!! Please try again!!");
                return -1;
            }

            int spellDamage = spell.getDamage() + hero.getDexterity() / 10000 * spell.getDamage();
            // if the hero is on the bush, the hero dexterity is increased by 10%
            if (((ValorCell) hero.getCurrentCell()).getCellType() == CellSpace.BUSH) {
                spellDamage = (int) (spellDamage + hero.getDexterity() / 10000 * spell.getDamage() * 1.1);
                Utils.println(MsgType.INFO + "The hero is on the bush, the hero dexterity is increased by 10%!");
            }
            monster.setCurrentHitPoints(monster.getCurrentHitPoints() - spell.getDamage());

            if (spell.getSpellType() == SpellType.FIRE) {
                monster.setDefense((int) (Math.floor(monster.getDefense() * 0.9)));
                Utils.println(MsgType.SUCCESS, "" + hero.getName() + " used " + spell.getName() + " spell on " + monster.getName() + "\nDecreasing the defense to " + monster.getDefense());
            } else if (spell.getSpellType() == SpellType.ICE) {
                monster.setBaseDamage((int) (Math.floor(monster.getBaseDamage() * 0.9)));
                Utils.println(MsgType.SUCCESS, "" + hero.getName() + " used " + spell.getName() + " spell on " + monster.getName() + "\nDecreasing the Base Damage to " + monster.getDefense());
            } else {
                monster.setDodge((int) (Math.floor(monster.getDodge() * 0.9)));
                Utils.println(MsgType.SUCCESS, "" + hero.getName() + " used " + spell.getName() + " spell on " + monster.getName() + "\nDecreasing the Dodge to " + monster.getDefense());
            }
            hero.setCurrentMagicPoints(hero.getCurrentMagicPoints() - spell.getMpCost());

            hero.getItemInventory().get(ItemType.SPELL).remove(selectedSpell);

            if (monster.getCurrentHitPoints() == 0) {
                Utils.println(MsgType.INFO, "" + monster.getName() + " died!! Congratulations!");
                return 1;
            }

            return 0;
        } else {
            System.out.println("Uh oh! The Hero is out of spells!! Please buy some from the market to try again!!");
            return -1;
        }
    }

    public static boolean equipWeaponArmor(ValorHero hero, ItemType itemType) throws IOException {
        Item equipItem;
        if (hero.isItemEquipped(itemType)) {
            Utils.println(MsgType.INFO, "Your hero already has a " + itemType + " equipped, would you like to replace it? (No - N/Yes - any key)");
            if ((Utils.input.readLine()).equalsIgnoreCase("N")) {
//                System.out.println("That sounds great!! Teleporting you back to the market :D");
                return false;
            }
            equipItem = hero.getItemEquipped(itemType);
            hero.getItemInventory().get(itemType).add(equipItem);
            hero.removeItemEquipped(itemType);
        }
        if (hero.getItemInventory().get(itemType).size() == 0) {
            Utils.println(MsgType.WARNING, "Uh oh! The Hero is out of " + itemType + "!! Please buy some from the market to try again!!");
            return false;
        }
        Utils.println(MsgType.INFO, "Here are the list of " + itemType + " which are there in the hero's inventory");
        if (itemType == ItemType.ARMOR) {
            Utils.displayArmors(hero.getItemInventory().get(itemType));
        } else {
            Utils.displayWeapons(hero.getItemInventory().get(itemType));
        }
        System.out.print("\n Please choose a " + itemType + " which you would like to equip : ");
        int selectedItem = Integer.parseInt(Utils.input.readLine()) - 1;

        if (selectedItem < 0 || selectedItem >= hero.getItemInventory().get(itemType).size()) {
            Utils.println(MsgType.WARNING, "Please choose a valid option!!");
            return false;
        }

        equipItem = (hero.getItemInventory().get(itemType).get(selectedItem));
        hero.setItemsEquipped(itemType, equipItem);
        hero.getItemInventory().get(itemType).remove(selectedItem);

        Utils.println(MsgType.SUCCESS, "" + hero.getName() + " is equipped with " + itemType + " " + equipItem.getName());
        return true;
    }

    public static boolean chancesOfDodge(Integer value) {
        return ((Utils.randomNumber.nextInt(value) * 2) % 7) == 0;
    }

    public static void attackOfMonster(ValorMonster monster, ValorHero hero) throws IOException {
        Integer heroAgility = hero.getAgility();
        // If hero is in the cave, then hero agility is increased by 10%
        if (((ValorCell) hero.getCurrentCell()).getCellType() == CellSpace.CAVE) {
            System.out.println(TextColors.YELLOW + "Hero is in the cave, so hero agility is increased by 10%.");
            heroAgility = (int) (heroAgility * 1.1);
        }
        if (!chancesOfDodge(heroAgility)) {
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
        System.out.println();
    }

    public static boolean attackOfHero(ValorHero hero, ValorMonster monster) throws Exception {
        if (!chancesOfDodge(monster.getDodge())) {
            int attackDamage = 0;
            int strength = hero.getStrength();
            // If hero is in the koulou, then strength is increased by 10%
            if (((ValorCell) hero.getCurrentCell()).getCellType() == CellSpace.KOULOU) {
                System.out.println(TextColors.YELLOW + "Hero is in the koulou, so hero sterngth is increased by 10%.");
                strength = (int) (strength * 1.1);
            }
            if (hero.isItemEquipped(ItemType.WEAPON)) {
                attackDamage = (int) ((strength + ((Weapon) hero.getItemEquipped(ItemType.WEAPON)).getDamage()) * 0.05);
            } else {
                attackDamage = (int) (strength * 0.05);
            }
            System.out.println("" + hero.getName() + " successfully attacked " + monster.getName() + " for " + attackDamage + "HP!");

            if (monster.getCurrentHitPoints() <= attackDamage) {
                File soundFile = new File("" + System.getProperty("user.dir") + "/src/sounds/" + "monster.wav");
                Clip clip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
                clip.open(ais);
                clip.start();
                System.out.println("" + monster.getName() + " died!! Congratulations!");
                monster.setCurrentHitPoints(monster.getMaxHitPoints());
                System.out.println();
                return true;    // Monster died return true
            } else {
                monster.setCurrentHitPoints(monster.getCurrentHitPoints() - attackDamage);
            }
        } else {
            System.out.println("" + monster.getName() + " dodged the attack!!");
        }
        System.out.println();
        return false;   // Monster is still alive return false
    }

    public static void checkHeroLevelUp(ValorHero hero) {
        if (hero.getExperiencePoints() > (hero.getLevel() * 15)) {
            hero.setLevel(hero.getLevel() + 1);
            hero.setExperiencePoints(0);
            hero.setMaxMagicPoints((int) Math.floor(hero.getMaxMagicPoints() * 1.1));
            hero.setCurrentMagicPoints(hero.getMaxMagicPoints());
            hero.setMaxHitPoints((int) Math.floor(hero.getMaxHitPoints() * 1.1));
            hero.setCurrentHitPoints(hero.getMaxHitPoints());
            hero.setStrength((int) Math.floor(hero.getStrength() * 1.05));
            hero.setAgility((int) Math.floor(hero.getAgility() * 1.05));
            hero.setDexterity((int) Math.floor(hero.getDexterity() * 1.05));
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
