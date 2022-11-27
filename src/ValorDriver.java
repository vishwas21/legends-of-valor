import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Valor Class: This is the driver class for the Legends of Valor game. The Starting point for everything in this game.
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 26, 2022
 */

public class ValorDriver {

    private static HashMap<String, ArrayList<Pawn>> heroMap;
    private static ArrayList<Pawn> monsterList;
    private static HashMap<ItemType, ArrayList<Item>> marketItemMap;
    private static Team teamHero;
    private static Team teamMonster;
    private static ValorLayout vLayout;

    public static HashMap<String, ArrayList<Pawn>> getHeroMap() {
        return heroMap;
    }

    public static ArrayList<Pawn> getMonsterList() {
        return monsterList;
    }

    public static HashMap<ItemType, ArrayList<Item>> getMarketItemMap() {
        return marketItemMap;
    }

    public static void initTeamHero() {
        teamHero = new Team();
        teamHero.initTeam();
    }

    public static void initTeamMonster() {
        teamMonster = new Team();
        teamMonster.initTeam();
    }

    public static Team getTeamHero() {
        return teamHero;
    }

    public static Team getTeamMonster() {
        return teamMonster;
    }

    public static void generateConstants() {
        heroMap = new HashMap<>();
        try {
            heroMap.put("Paladins", NPCGenerator.generatePawn(NPCType.HERO, new ArrayList<>(), "Paladins.txt"));
            heroMap.put("Sorcerers", NPCGenerator.generatePawn(NPCType.HERO, new ArrayList<>(), "Sorcerers.txt"));
            heroMap.put("Warriors", NPCGenerator.generatePawn(NPCType.HERO, new ArrayList<>(), "Warriors.txt"));

            monsterList = NPCGenerator.generatePawn(NPCType.MONSTER, new ArrayList<>(), "Dragons.txt");
            monsterList = NPCGenerator.generatePawn(NPCType.MONSTER, monsterList, "Exoskeletons.txt");
            monsterList = NPCGenerator.generatePawn(NPCType.MONSTER, monsterList, "Spirits.txt");

            marketItemMap = new HashMap<>();
            marketItemMap.put(ItemType.WEAPON, ItemGenerator.generateItem(ItemType.WEAPON, new ArrayList<>(), "Weaponry.txt"));
            marketItemMap.put(ItemType.ARMOR, ItemGenerator.generateItem(ItemType.ARMOR, new ArrayList<>(), "Armory.txt"));
            marketItemMap.put(ItemType.POTION, ItemGenerator.generateItem(ItemType.POTION, new ArrayList<>(), "Potions.txt"));
            marketItemMap.put(ItemType.SPELL, ItemGenerator.generateItem(ItemType.SPELL, new ArrayList<>(), "FireSpells.txt"));
            marketItemMap.put(ItemType.SPELL, ItemGenerator.generateItem(ItemType.SPELL, marketItemMap.get(ItemType.SPELL), "IceSpells.txt"));
            marketItemMap.put(ItemType.SPELL, ItemGenerator.generateItem(ItemType.SPELL, marketItemMap.get(ItemType.SPELL), "LightningSpells.txt"));
        } catch (IOException error) {
            System.out.println("Internal Server Error : " + error);
            error.printStackTrace();
        }
    }

    public static void printHeroes(String heroType) {
        System.out.println("Here are the list of " + heroType + " and their stats. \nEnter the number associated with each hero to choose that hero in your team");
        printChar(TextColors.WHITE, '-', 152);
        System.out.println();
        ValorHero.printHeaders();
        printChar(TextColors.WHITE, '-', 152);
        System.out.println();
        for (int index = 0; index < heroMap.get(heroType).size(); index ++) {
            ((ValorHero)heroMap.get(heroType).get(index)).printData(index + 1);
        }
        printChar(TextColors.WHITE, '-', 152);
        System.out.println();
    }

    public static void buildHeroTeam(Team teamHero) {
        System.out.println("Let us build a team! \n There are multiple hero types. Please choose a number from the list shown");
        int heroChoice;
        String heroType;

        for (int i = 1; i <= teamHero.getTeamSize(); ) {
            System.out.println("Hero Number " + i);
            System.out.println("Please choose a number from the list of hero types:\n1. Paladins\n2. Sorcerers\n3. Warriors");
            System.out.print("Input : ");
            try {
                heroChoice = Integer.parseInt(Utils.input.readLine());
                if (heroChoice == 1) {
                    heroType = "Paladins";
                } else if (heroChoice == 2) {
                    heroType = "Sorcerers";
                } else if (heroChoice == 3) {
                    heroType = "Warriors";
                } else {
                    System.out.println("Invalid Entry!! Please choose between the options provided!");
                    continue;
                }
                printHeroes(heroType);
                System.out.print("Please type the number of the hero : ");
                heroChoice = Integer.parseInt(Utils.input.readLine());
                heroMap.get(heroType).get(heroChoice - 1).setSymbol("H" + i);
                Cell nexusCell = vLayout.getCell(i - 1, vLayout.getLength() - 1, 0);
                ((ValorCell)nexusCell).setHero(heroMap.get(heroType).get(heroChoice - 1));
                ((ValorHero)(heroMap.get(heroType).get(heroChoice - 1))).setNexusCell(nexusCell);
                ((ValorHero)(heroMap.get(heroType).get(heroChoice - 1))).setCurrentCell(nexusCell);
                teamHero.addPawn(heroMap.get(heroType).get(heroChoice - 1));
                heroMap.get(heroType).remove(heroChoice - 1);
                System.out.println("Hero added successfully to your team!! \n\n");
                i ++;
            } catch (Exception error) {
                System.out.println("Error Encountered! Stack Trace : ");
                error.printStackTrace();
            }
        }
    }

    public static void buildMonsterTeam(Team teamMonster, Team teamHero) {
        Integer maxMonsterLevel = 0, minMonsterLevel = 99;

        teamMonster.increaseTeamSize(3);

        for (Pawn hero: teamHero.getPawnList()) {
            if (((ValorHero)hero).getLevel() > maxMonsterLevel) {
                maxMonsterLevel = ((ValorHero) hero).getLevel();
            }
            if (((ValorHero)hero).getLevel() < minMonsterLevel) {
                minMonsterLevel = ((ValorHero) hero).getLevel();
            }
        }

        Pawn selectedMonster;
        for (int index = teamMonster.getTeamSize() - 3; index < teamMonster.getTeamSize(); ) {
            selectedMonster = monsterList.get(Utils.randomNumber.nextInt(monsterList.size()));
            if (((ValorMonster) selectedMonster).getLevel() >= minMonsterLevel && ((ValorMonster) selectedMonster).getLevel() <= maxMonsterLevel) {
                selectedMonster = ((ValorMonster)selectedMonster).clone();
                Cell nexusCell = vLayout.getCell((index % 3), 0, 1);
                ((ValorCell)nexusCell).setMonster(selectedMonster);
                ((ValorMonster)(selectedMonster)).setNexusCell(nexusCell);
                ((ValorMonster)(selectedMonster)).setCurrentCell(nexusCell);
                selectedMonster.setSymbol("M" + (index + 1));
                teamMonster.addPawn(selectedMonster);
                index ++;
            }
        }
    }

    public static void printChar(String textColor, Character ch, Integer length) {
        System.out.print(textColor);
        for (int count = 0; count < length; count ++) {
            System.out.print(ch);
        }
        System.out.print(TextColors.RESET);
    }

    public static void playGame() throws Exception {

        do {

            try {
                printChar(TextColors.CYAN, '*', 60);
                System.out.print(TextColors.BLUE + " Legends: " + TextColors.PURPLE + "Heroes " + TextColors.GREEN + "and " + TextColors.RED + "Monsters " + TextColors.RESET + "");
                printChar(TextColors.CYAN, '*', 60);
                System.out.println();

                // TODO: Add the required print statements

                generateConstants();

                vLayout = new ValorLayout(8);

                do {
                    vLayout.initLayout();
                    vLayout.displayLayout();

                    System.out.println("This is the layout of the map! Would you like to regenerate the map? (Y/N)");
                    System.out.print("Input: ");
                } while (Utils.input.readLine().equalsIgnoreCase("Y"));

                initTeamHero();
                teamHero.setTeamSize(3);
                buildHeroTeam(teamHero);
                initTeamMonster();
                teamMonster.setTeamSize(0);
                buildMonsterTeam(teamMonster, teamHero);

                System.out.println("\n\n\n");
                vLayout.displayLayout();
                System.out.println("This is the latest state of the layout with the heroes team present :)");

                String playedMove;
                System.out.println("\nAll the best\n");

            } catch (Exception error) {
                System.out.println("Uh oh!! The game crashed! Apologise for the inconvenience!! \nError " + error.getMessage());
            }

            System.out.println("I hope you saved your world and had fun while doing it ");
            System.out.print("Do you want to play again? (Y/N) ");
        } while (Utils.input.readLine().equalsIgnoreCase("Y"));

    }
}
