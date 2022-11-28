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
        for (int index = 0; index < heroMap.get(heroType).size(); index++) {
            ((ValorHero) heroMap.get(heroType).get(index)).printData(index + 1);
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
                ((ValorCell) nexusCell).setHero(heroMap.get(heroType).get(heroChoice - 1));
                ((ValorHero) (heroMap.get(heroType).get(heroChoice - 1))).setNexusCell(nexusCell);
                ((ValorHero) (heroMap.get(heroType).get(heroChoice - 1))).setCurrentCell(nexusCell);
                teamHero.addPawn(heroMap.get(heroType).get(heroChoice - 1));
                heroMap.get(heroType).remove(heroChoice - 1);
                System.out.println("Hero added successfully to your team!! \n\n");
                i++;
            } catch (Exception error) {
                System.out.println("Error Encountered! Stack Trace : ");
                error.printStackTrace();
            }
        }
    }

    public static void buildMonsterTeam(Team teamMonster, Team teamHero) {
        Integer maxMonsterLevel = 0, minMonsterLevel = 99;

        teamMonster.increaseTeamSize(3);

        for (Pawn hero : teamHero.getPawnList()) {
            if (((ValorHero) hero).getLevel() > maxMonsterLevel) {
                maxMonsterLevel = ((ValorHero) hero).getLevel();
            }
            if (((ValorHero) hero).getLevel() < minMonsterLevel) {
                minMonsterLevel = ((ValorHero) hero).getLevel();
            }
        }

        Pawn selectedMonster;
        for (int index = teamMonster.getTeamSize() - 3; index < teamMonster.getTeamSize(); ) {
            selectedMonster = monsterList.get(Utils.randomNumber.nextInt(monsterList.size()));
            if (((ValorMonster) selectedMonster).getLevel() >= minMonsterLevel && ((ValorMonster) selectedMonster).getLevel() <= maxMonsterLevel) {
                selectedMonster = ((ValorMonster) selectedMonster).clone();
                Cell nexusCell = vLayout.getCell((index % 3), 0, 1);
                ((ValorCell) nexusCell).setMonster(selectedMonster);
                ((ValorMonster) (selectedMonster)).setNexusCell(nexusCell);
                ((ValorMonster) (selectedMonster)).setCurrentCell(nexusCell);
                selectedMonster.setSymbol("M" + (index + 1));
                teamMonster.addPawn(selectedMonster);
                index++;
            }
        }
    }

    public static void printChar(String textColor, Character ch, Integer length) {
        System.out.print(textColor);
        for (int count = 0; count < length; count++) {
            System.out.print(ch);
        }
        System.out.print(TextColors.RESET);
    }

    private static boolean checkValidMovement(Integer laneNumber, Integer positionX, Integer positionY) {
        // Check if inside the lane
        if (0 <= positionX && positionX <= vLayout.getLength() - 1
                && 0 <= positionY && positionY <= 1) {
            // Check if the cell is empty
            if (!((ValorCell) (vLayout.getCell(laneNumber, positionX, positionY))).isHeroPresent()) {
                System.out.println("Cell is empty");
                // Check if crossed the monster in the same lane
                // iterate the cells below to check if there is a monster
                for (int index = positionX + 1; index < vLayout.getLength(); index++) {
                    if (((ValorCell) (vLayout.getCell(laneNumber, index, 0))).isMonsterPresent()
                            || ((ValorCell) (vLayout.getCell(laneNumber, index, 1))).isMonsterPresent()) {
                        return false;
                    }
                }
                return true;
            } else {
                System.out.println("Cell is not empty");
            }
        } else {
            System.out.println("Outside the lane");
        }
        return false;
    }

    private static void setHeroPosition(ValorHero hero, Integer laneNumber, Integer positionX, Integer positionY) {
        // remove the hero from the current cell
        ((ValorCell) (hero.getCurrentCell())).removeHero();
        // set the hero in the new cell
        hero.setCurrentCell(vLayout.getCell(laneNumber, positionX, positionY));
        ((ValorCell) vLayout.getCell(laneNumber, positionX, positionY)).setHero(hero);
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

                while (true) {
                    String playedMove;
                    // Each hero gets to play a move
                    for (Pawn pawn : teamHero.getPawnList()) {
                        ValorHero hero = (ValorHero) pawn;
                        while (true) {
                            System.out.println("It is " + hero.getName() + "'s turn to play a move!");
                            vLayout.displayLayout();
                            System.out.println("Current Position : " + ((ValorCell) hero.getCurrentCell()).getLaneNumber()
                                    + " " + ((ValorCell) hero.getCurrentCell()).getLaneIndexX()
                                    + " " + ((ValorCell) hero.getCurrentCell()).getLaneIndexY());
                            System.out.println("\nFollowing are the rules for movements in the game!! Please press the right button to play the game!");
                            System.out.println("W - Move Up");
                            System.out.println("A - Move Left");
                            System.out.println("S - Move Down");
                            System.out.println("D - Move Right");
                            System.out.println("Q - Quit Game");
                            System.out.print("What would you like to do? ");
                            playedMove = Utils.input.readLine();
                            ValorCell currentCell = (ValorCell) hero.getCurrentCell();
                            int newLane = currentCell.getLaneNumber();
                            int newX = currentCell.getLaneIndexX();
                            int newY = currentCell.getLaneIndexY();
                            if (playedMove.equalsIgnoreCase("W") || playedMove.equalsIgnoreCase("S") || playedMove.equalsIgnoreCase("A") || playedMove.equalsIgnoreCase("D")) {
                                System.out.println("Move");
                                if (playedMove.equalsIgnoreCase("W")) {
                                    newX--;
                                } else if (playedMove.equalsIgnoreCase("S")) {
                                    newX++;
                                } else if (playedMove.equalsIgnoreCase("A")) {
                                    newY--;
                                } else if (playedMove.equalsIgnoreCase("D")) {
                                    newY++;
                                }
                                System.out.println("New Position : " + newLane + " " + newX + " " + newY);
                                if (checkValidMovement(newLane, newX, newY)) {
                                    setHeroPosition(hero, newLane, newX, newY);
                                    break;
                                } else {
                                    System.out.println("Invalid Move! Please try again!");
                                    break;
                                }
                            } else if (playedMove.equalsIgnoreCase("Q")) {
                                System.out.println("You have quit the game! Thank you for playing!");
                                System.exit(0);
                            } else {
                                System.out.println("Invalid Input! Please try again!");
                            }
                        }
                    }
                    // For each monster to make a move if valid
                    for (Pawn pawn : teamMonster.getPawnList()) {
                        ValorMonster monster = (ValorMonster) pawn;
                        ValorCell currentCell = (ValorCell) monster.getCurrentCell();
                        int newLane = currentCell.getLaneNumber();
                        int newX = currentCell.getLaneIndexX();
                        int newY = currentCell.getLaneIndexY();
                        if (newX < vLayout.getLength() - 1) {
                            if (!((ValorCell) vLayout.getCell(newLane, newX, 0)).isHeroPresent()
                                    && !((ValorCell) vLayout.getCell(newLane, newX, 1)).isHeroPresent()) {
                                // Move down
                                ((ValorCell) monster.getCurrentCell()).removeMonster();
                                monster.setCurrentCell(vLayout.getCell(newLane, newX+1, newY));
                                ((ValorCell) vLayout.getCell(newLane, newX+1, newY)).setMonster(monster);
                            }
                        }
                    }
                }
            } catch (Exception error) {
                System.out.println("Uh oh!! The game crashed! Apologise for the inconvenience!! \nError " + error.getMessage());
            }

            System.out.println("I hope you saved your world and had fun while doing it ");
            System.out.print("Do you want to play again? (Y/N) ");
        } while (Utils.input.readLine().equalsIgnoreCase("Y"));
    }
}