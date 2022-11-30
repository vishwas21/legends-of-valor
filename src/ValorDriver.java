/**
 * ValorDriver Class: This is the driver class for the Legends of Valor game. The Starting point for everything in this game.
 */

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ValorDriver {

    private static HashMap<String, ArrayList<Pawn>> heroMap;
    private static ArrayList<Pawn> monsterList;
    private static HashMap<ItemType, ArrayList<Item>> marketItemMap;
    private static Team teamHero;
    private static Team teamMonster;
    private static ValorLayout vLayout;
    private static Integer currentRound;

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
        Utils.println(MsgType.INFO, "Let us build a team! \n There are multiple hero types. Please choose a number from the list shown");
        int heroChoice;
        String heroType;

        for (int i = 1; i <= teamHero.getTeamSize(); ) {
            Utils.println(MsgType.INFO, "Hero Number " + i);
            Utils.println(MsgType.INFO, "Please choose a number from the list of hero types:\n1. Paladins\n2. Sorcerers\n3. Warriors");
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
                    Utils.println(MsgType.WARNING, "Invalid Entry!! Please choose between the options provided!");
                    continue;
                }
                printHeroes(heroType);
                Utils.println(MsgType.INFO, "Please type the number of the hero : ");
                heroChoice = Integer.parseInt(Utils.input.readLine());
                heroMap.get(heroType).get(heroChoice - 1).setSymbol("H" + i);
                Cell nexusCell = vLayout.getCell(i - 1, vLayout.getLength() - 1, 0);
                ((ValorCell) nexusCell).setHero(heroMap.get(heroType).get(heroChoice - 1));
                ((ValorHero) (heroMap.get(heroType).get(heroChoice - 1))).setNexusCell(nexusCell);
                ((ValorHero) (heroMap.get(heroType).get(heroChoice - 1))).setCurrentCell(nexusCell);
                teamHero.addPawn(heroMap.get(heroType).get(heroChoice - 1));
                heroMap.get(heroType).remove(heroChoice - 1);
                Utils.println(MsgType.SUCCESS, "Hero added successfully to your team!! \n\n");
                i++;
            } catch (Exception error) {
                Utils.println(MsgType.ERROR, "Error Encountered! Stack Trace : ");
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
                selectedMonster = ((ValorMonster) selectedMonster).cloneObj();
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

    private static boolean checkValidMovement(Integer laneNumber, Integer positionX, Integer positionY, boolean debug) {
        // Check if inside the lane
        if (0 <= positionX && positionX <= vLayout.getLength() - 1
                && 0 <= positionY && positionY <= 1) {
            // Check if the cell is empty
            if (!((ValorCell) (vLayout.getCell(laneNumber, positionX, positionY))).isHeroPresent()) {
                // Check if crossed the monster in the same lane
                // iterate the cells below to check if there is a monster
                for (int index = positionX + 1; index < vLayout.getLength(); index++) {
                    if (((ValorCell) (vLayout.getCell(laneNumber, index, 0))).isMonsterPresent()
                            || ((ValorCell) (vLayout.getCell(laneNumber, index, 1))).isMonsterPresent()) {
                        Utils.println(MsgType.WARNING, "You cannot cross the monsters in the same lane!");
                        return false;
                    }
                }
                return true;
            } else {
                if (debug) {
                    Utils.println(MsgType.WARNING, "There is already a hero present in the cell!");
                }
            }
        } else {
            if (debug) {
                Utils.println(MsgType.WARNING, "You cannot move outside the lane!");
            }
        }
        return false;
    }

    private static void setHeroPosition(ValorHero hero, Integer laneNumber, Integer positionX, Integer positionY) {
        // remove the hero from the current cell
        ((ValorCell) (hero.getCurrentCell())).removeHero();
        // set the hero in the new cell
        hero.setCurrentCell(vLayout.getCell(laneNumber, positionX, positionY));
        ((ValorCell) vLayout.getCell(laneNumber, positionX, positionY)).setHero(hero);
        // set cell and neighbor discovered
        ((ValorCell) vLayout.getCell(laneNumber, positionX, 0)).setDiscovered(true);
        ((ValorCell) vLayout.getCell(laneNumber, positionX, 1)).setDiscovered(true);
    }

    private static List<Pawn> findTargetsInRange(Pawn pawn) {
        List<Pawn> targetsInRange = new ArrayList<>();
        // find the pawns that are different type and in range
        if (pawn.getType().equals(String.valueOf(NPCType.HERO))) {
            // For heroes, find monsters in range
            ValorHero hero = (ValorHero) pawn;
            int laneNumber = ((ValorCell) hero.getCurrentCell()).getLaneNumber();
            int indexX = ((ValorCell) hero.getCurrentCell()).getLaneIndexX();
            int indexY = ((ValorCell) hero.getCurrentCell()).getLaneIndexY();
            // check the current cell
            if (((ValorCell) hero.getCurrentCell()).isMonsterPresent()) {
                targetsInRange.add(((ValorCell) hero.getCurrentCell()).getMonster());
            }
            // check the neighbor cell
            if ((vLayout.getCell(laneNumber, indexX, (indexY + 1) % 2)) != null) {
                if (((ValorCell) vLayout.getCell(laneNumber, indexX, (indexY + 1) % 2)).isMonsterPresent()) {
                    targetsInRange.add(((ValorCell) vLayout.getCell(laneNumber, indexX, (indexY + 1) % 2)).getMonster());
                }
            }
            // check the above cell
            if ((vLayout.getCell(laneNumber, indexX - 1, indexY)) != null) {
                if (((ValorCell) vLayout.getCell(laneNumber, indexX - 1, indexY)).isMonsterPresent()) {
                    targetsInRange.add(((ValorCell) vLayout.getCell(laneNumber, indexX - 1, indexY)).getMonster());
                }
            }
            // check the above neighbor cell
            if ((vLayout.getCell(laneNumber, indexX - 1, (indexY + 1) % 2)) != null) {
                if (((ValorCell) vLayout.getCell(laneNumber, indexX - 1, (indexY + 1) % 2)).isMonsterPresent()) {
                    targetsInRange.add(((ValorCell) vLayout.getCell(laneNumber, indexX - 1, (indexY + 1) % 2)).getMonster());
                }
            }
        } else {
            // For monsters, find heroes in range
            ValorMonster monster = (ValorMonster) pawn;
            int laneNumber = ((ValorCell) monster.getCurrentCell()).getLaneNumber();
            int indexX = ((ValorCell) monster.getCurrentCell()).getLaneIndexX();
            int indexY = ((ValorCell) monster.getCurrentCell()).getLaneIndexY();
            // check the current cell
            if (((ValorCell) monster.getCurrentCell()).isHeroPresent()) {
                targetsInRange.add(((ValorCell) monster.getCurrentCell()).getHero());
            }
            // check the neighbor cell
            if ((vLayout.getCell(laneNumber, indexX, (indexY + 1) % 2)) != null) {
                if (((ValorCell) vLayout.getCell(laneNumber, indexX, (indexY + 1) % 2)).isHeroPresent()) {
                    targetsInRange.add(((ValorCell) vLayout.getCell(laneNumber, indexX, (indexY + 1) % 2)).getHero());
                }
            }
            // check the below cell
            if ((vLayout.getCell(laneNumber, indexX + 1, indexY)) != null) {
                if (((ValorCell) vLayout.getCell(laneNumber, indexX + 1, indexY)).isHeroPresent()) {
                    targetsInRange.add(((ValorCell) vLayout.getCell(laneNumber, indexX + 1, indexY)).getHero());
                }
            }
            // check the below neighbor cell
            if ((vLayout.getCell(laneNumber, indexX + 1, (indexY + 1) % 2)) != null) {
                if (((ValorCell) vLayout.getCell(laneNumber, indexX + 1, (indexY + 1) % 2)).isHeroPresent()) {
                    targetsInRange.add(((ValorCell) vLayout.getCell(laneNumber, indexX + 1, (indexY + 1) % 2)).getHero());
                }
            }
        }
        return targetsInRange;
    }

    private static boolean handleBasicMove(ValorHero hero, String playedMove) {
        ValorCell currentCell = (ValorCell) hero.getCurrentCell();
        int newLane = currentCell.getLaneNumber();
        int newX = currentCell.getLaneIndexX();
        int newY = currentCell.getLaneIndexY();
        if (playedMove.equalsIgnoreCase("W")) {
            newX--;
        } else if (playedMove.equalsIgnoreCase("S")) {
            newX++;
        } else if (playedMove.equalsIgnoreCase("A")) {
            newY--;
        } else if (playedMove.equalsIgnoreCase("D")) {
            newY++;
        }
        if (checkValidMovement(newLane, newX, newY, true)) {
            setHeroPosition(hero, newLane, newX, newY);
            return true;
        } else {
            return false;
        }
    }

    private static boolean handleRecall(ValorHero hero) {
        // Check if the nexus is empty
        ValorCell nexusCell = (ValorCell) hero.getNexusCell();
        if (nexusCell == hero.getCurrentCell()) {
            Utils.println(MsgType.WARNING, "You are already in the origin Nexus cell!");
            return false;
        }
        if (!nexusCell.isHeroPresent()) {
            // remove the hero from the current cell
            ((ValorCell) (hero.getCurrentCell())).removeHero();
            // set the hero in the new cell
            hero.setCurrentCell(nexusCell);
            nexusCell.setHero(hero);
            return true;
        } else {
            // Check if the cell next to the origin nexus cell is empty
            if (((ValorCell) vLayout.getCell(nexusCell.getLaneNumber(), nexusCell.getLaneIndexX(), (nexusCell.getLaneIndexY() + 1) % 2)).isHeroPresent()) {
                Utils.println(MsgType.WARNING, "The origin Nexus cell is occupied by another hero!");
                return false;
            }
            // remove the hero from the current cell
            ((ValorCell) (hero.getCurrentCell())).removeHero();
            // set the hero in the new cell
            hero.setCurrentCell(vLayout.getCell(nexusCell.getLaneNumber(), nexusCell.getLaneIndexX(), (nexusCell.getLaneIndexY() + 1) % 2));
            ((ValorCell) vLayout.getCell(nexusCell.getLaneNumber(), nexusCell.getLaneIndexX(), (nexusCell.getLaneIndexY() + 1) % 2)).setHero(hero);
            return true;
        }
    }

    private static boolean handleTeleport(ValorHero hero) throws IOException {
        Utils.println(MsgType.INFO, "Which hero would you like to teleport to?");
        Team otherTeam = teamHero.clone();
        otherTeam.removePawn(hero);
        Team.displayTeam(otherTeam, "Heroes");
        System.out.print("Input: ");
        int heroNumber = Integer.parseInt(Utils.input.readLine()) - 1;
        // Check available cells near the hero
        ValorHero targetHero = (ValorHero) otherTeam.getPawnAtIndex(heroNumber);
        ValorCell targetCell = (ValorCell) targetHero.getCurrentCell();
        int targetLane = targetCell.getLaneNumber();
        int targetX = targetCell.getLaneIndexX();
        int targetY = targetCell.getLaneIndexY();
        List<ValorCell> availableCells = new ArrayList<>();
        // Check the cell below the hero
        if (checkValidMovement(targetLane, targetX + 1, targetY, false)) {
            availableCells.add((ValorCell) vLayout.getCell(targetLane, targetX + 1, targetY));
        }
        // Check the cell next to the hero
        if (checkValidMovement(targetLane, targetX, (targetY + 1) % 2, false)) {
            availableCells.add((ValorCell) vLayout.getCell(targetLane, targetX, (targetY + 1) % 2));
        }
        // Let the user choose the cell
        if (availableCells.size() > 0) {
            System.out.println("Which cell would you like to teleport to?");
            for (int i = 0; i < availableCells.size(); i++) {
                System.out.println((i + 1) + " - Lane" + availableCells.get(i).getLaneNumber() + " X" + availableCells.get(i).getLaneIndexX() + " Y" + availableCells.get(i).getLaneIndexY());
            }
            System.out.print("Input: ");
            int cellNumber = Integer.parseInt(Utils.input.readLine()) - 1;
            if (cellNumber >= 0 && cellNumber < availableCells.size()) {
                ValorCell newCell = availableCells.get(cellNumber);
                setHeroPosition(hero, newCell.getLaneNumber(), newCell.getLaneIndexX(), newCell.getLaneIndexY());
                Utils.println(MsgType.SUCCESS, "Hero teleported to Lane" + newCell.getLaneNumber() + " X" + newCell.getLaneIndexX() + " Y" + newCell.getLaneIndexY());
                return true;
            } else {
                Utils.println(MsgType.WARNING, "Invalid choice!");
                return false;
            }
        } else {
            Utils.println(MsgType.WARNING, "No available cells");
            return false;
        }
    }

    private static boolean handleAttack(ValorHero hero) throws Exception {
        // Check if any Monsters are in range
        List<Pawn> monstersInRange = findTargetsInRange(hero);
        if (monstersInRange.size() > 0) {
            // Let the user choose the target
            Utils.println(MsgType.INFO, "Which monster would you like to attack?");
            int monsterNumber = chooseTarget(monstersInRange);
            if (monsterNumber >= 0 && monsterNumber < monstersInRange.size()) {
                File soundFile = new File("" + System.getProperty("user.dir") + "/src/sounds/" + "battle.wav");
                Clip clip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
                clip.open(ais);
                clip.start();
                // Attack the monster
                ValorMonster monster = (ValorMonster) monstersInRange.get(monsterNumber);
                if (BattleDriver.attackOfHero(hero, monster)) {
                    // If the monster is dead, remove it from the board and the team
                    ((ValorCell) monster.getCurrentCell()).removeMonster();
                    teamMonster.removePawn(monster);
                    // Give all not-fainted heroes rewards
                    for (Pawn uf_pawn : teamHero.getPawnList()) {
                        ValorHero uf_hero = (ValorHero) uf_pawn;
                        if (!uf_hero.getDidFaint()) {
                            uf_hero.setGold(uf_hero.getGold() + 500 * monster.getLevel());
                            uf_hero.setExperiencePoints(uf_hero.getExperiencePoints() + 2 * monster.getLevel());
                            BattleDriver.checkHeroLevelUp(uf_hero);
                        }
                    }
                }
                return true;
            } else {
                Utils.println(MsgType.WARNING, "Invalid choice!");
                return false;
            }
        } else {
            Utils.println(MsgType.WARNING, "No monsters in range!");
            return false;
        }
    }

    private static boolean handleSpell(ValorHero hero) throws IOException {
        // Find available targets first
        List<Pawn> targets = findTargetsInRange(hero);
        if (targets.size() == 0) {
            Utils.println(MsgType.WARNING, "No targets in range!");
            return false;
        }
        // Let the user choose the target
        Utils.println(MsgType.INFO, "Which target would you like to choose?");
        int targetNumber = chooseTarget(targets);
        if (targetNumber >= 0 && targetNumber < targets.size()) {
            int result = BattleDriver.useSpell(hero, (ValorMonster) targets.get(targetNumber));
            if (result == 1) {
                // If the monster is dead, remove it from the board and the team
                ((ValorCell) ((ValorMonster) targets.get(targetNumber)).getCurrentCell()).removeMonster();
                teamMonster.removePawn(targets.get(targetNumber));
                // Give all not-fainted heroes rewards
                for (Pawn uf_pawn : teamHero.getPawnList()) {
                    ValorHero uf_hero = (ValorHero) uf_pawn;
                    if (!uf_hero.getDidFaint()) {
                        uf_hero.setGold(uf_hero.getGold() + 500 * ((ValorMonster) targets.get(targetNumber)).getLevel());
                        uf_hero.setExperiencePoints(uf_hero.getExperiencePoints() + 2 * ((ValorMonster) targets.get(targetNumber)).getLevel());
                        BattleDriver.checkHeroLevelUp(uf_hero);
                    }
                }
            } else if (result < 0) {
                return false;
            }
            return true;
        } else {
            Utils.println(MsgType.WARNING, "Invalid choice!");
            return false;
        }
    }

    private static int chooseTarget(List<Pawn> targets) throws IOException {
        for (int i = 0; i < targets.size(); i++) {
            System.out.println((i + 1) + " - " + targets.get(i).getName());
        }
        System.out.print("Input: ");
        int targetNumber = Integer.parseInt(Utils.input.readLine()) - 1;
        return targetNumber;
    }

    private static boolean handleEquip(ValorHero hero) throws IOException {
        Utils.println(MsgType.INFO, "Which kind of item would you like to equip?");
        System.out.println("1 - Weapon");
        System.out.println("2 - Armor");
        int itemType = Integer.parseInt(Utils.input.readLine());
        if (itemType == 1) {
            if (BattleDriver.equipWeaponArmor(hero, ItemType.WEAPON)) {
                return true;
            } else {
                return false;
            }
        } else if (itemType == 2) {
            if (BattleDriver.equipWeaponArmor(hero, ItemType.ARMOR)) {
                return true;
            } else {
                return false;
            }
        } else {
            Utils.println(MsgType.WARNING, "Invalid choice!");
            return false;
        }
    }

    private static void handleMonster(ValorMonster monster) throws Exception {
        ValorCell currentCell = (ValorCell) monster.getCurrentCell();
        int newLane = currentCell.getLaneNumber();
        int newX = currentCell.getLaneIndexX();
        int newY = currentCell.getLaneIndexY();
        if (newX < vLayout.getLength() - 1) {
            List<Pawn> heroes = findTargetsInRange(monster);
            // if no heroes in range, move forward
            if (heroes.size() == 0) {
                // If cell not occupied
                if (!((ValorCell) vLayout.getCell(newLane, newX + 1, newY)).isMonsterPresent()) {
                    // Move down
                    ((ValorCell) monster.getCurrentCell()).removeMonster();
                    monster.setCurrentCell(vLayout.getCell(newLane, newX + 1, newY));
                    ((ValorCell) vLayout.getCell(newLane, newX + 1, newY)).setMonster(monster);
                    // Check if monsters win
                    if (((ValorCell) monster.getCurrentCell()).getLaneIndexX() == vLayout.getLength() - 1) {
                        Utils.println(TextColors.RED + "The monster has reached the end! You lose!");
                        System.out.println(TextColors.RESET);
                        System.out.println("Do you want to play again? (Y/N)");
                        String playAgain = Utils.input.readLine();
                        if (playAgain.equalsIgnoreCase("Y")) {
                            playGame();
                        } else {
                            System.exit(0);
                        }
                    }
                }
            }
            // if there are heroes in range, attack randomly
            else {
                int randomHero = (int) (Math.random() * heroes.size());
                ValorHero targetHero = (ValorHero) heroes.get(randomHero);
                BattleDriver.attackOfMonster(monster, targetHero);
                BattleDriver.checkBattleStatus(teamHero, teamMonster);
            }
        }
    }

    public static void playGame() throws Exception {

        do {

            File soundFile = new File("" + System.getProperty("user.dir") + "/src/sounds/" + "Akatsuki.wav");
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            clip.open(ais);
            clip.start();

            try {
                Utils.println(TextColors.GREEN + "$$\\                                                    $$\\                            $$$$$$\\        $$\\    $$\\          $$\\                     \n" +
                        "$$ |                                                   $$ |                          $$  __$$\\       $$ |   $$ |         $$ |                    \n" +
                        "$$ |      $$$$$$\\   $$$$$$\\   $$$$$$\\  $$$$$$$\\   $$$$$$$ | $$$$$$$\\        $$$$$$\\  $$ /  \\__|      $$ |   $$ |$$$$$$\\  $$ | $$$$$$\\   $$$$$$\\  \n" +
                        "$$ |     $$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$ |$$  _____|      $$  __$$\\ $$$$\\           \\$$\\  $$  |\\____$$\\ $$ |$$  __$$\\ $$  __$$\\ \n" +
                        "$$ |     $$$$$$$$ |$$ /  $$ |$$$$$$$$ |$$ |  $$ |$$ /  $$ |\\$$$$$$\\        $$ /  $$ |$$  _|           \\$$\\$$  / $$$$$$$ |$$ |$$ /  $$ |$$ |  \\__|\n" +
                        "$$ |     $$   ____|$$ |  $$ |$$   ____|$$ |  $$ |$$ |  $$ | \\____$$\\       $$ |  $$ |$$ |              \\$$$  / $$  __$$ |$$ |$$ |  $$ |$$ |      \n" +
                        "$$$$$$$$\\\\$$$$$$$\\ \\$$$$$$$ |\\$$$$$$$\\ $$ |  $$ |\\$$$$$$$ |$$$$$$$  |      \\$$$$$$  |$$ |               \\$  /  \\$$$$$$$ |$$ |\\$$$$$$  |$$ |      \n" +
                        "\\________|\\_______| \\____$$ | \\_______|\\__|  \\__| \\_______|\\_______/        \\______/ \\__|                \\_/    \\_______|\\__| \\______/ \\__|      \n" +
                        "                   $$\\   $$ |                                                                                                                    \n" +
                        "                   \\$$$$$$  |                                                                                                                    \n" +
                        "                    \\______/" + TextColors.RESET);
                printChar(TextColors.CYAN, '*', 60);
                System.out.print(TextColors.BLUE + " Legends " + TextColors.PURPLE + "of " + TextColors.GREEN + "Valor" + TextColors.RESET + "");
                printChar(TextColors.CYAN, '*', 60);
                System.out.println();

                // TODO: Add the required print statements

                generateConstants();

                vLayout = new ValorLayout(8);

                do {
                    vLayout.initLayout();
                    vLayout.displayLayout();

                    Utils.println(MsgType.INFO, "This is the layout of the map! Would you like to regenerate the map? (Y/N)");
                    System.out.print("Input: ");
                } while (Utils.input.readLine().equalsIgnoreCase("Y"));

                initTeamHero();
                teamHero.setTeamSize(3);
                buildHeroTeam(teamHero);
                initTeamMonster();
                teamMonster.setTeamSize(0);

                Utils.println(MsgType.INFO, "This is the team of Heroes : ");
                Team.displayTeam(teamHero, "heroes");

                System.out.println("Hit Enter to continue :)");
                Utils.input.readLine();

                System.out.println("\n\n\n");
                vLayout.displayLayout();
                Utils.println(MsgType.INFO, "This is the latest state of the layout with the heroes team present :)");

                System.out.println("Hit Enter to continue :)");
                Utils.input.readLine();

                currentRound = 1;

                mainLoop: while (true) {
                    Utils.println(MsgType.INFO, "Round " + currentRound + " :");
                    if (currentRound == 1 || currentRound % 8 == 0) {
                        buildMonsterTeam(teamMonster, teamHero);
                        System.out.println("\n\n Below are the team of Monsters :");
                        Team.displayTeam(teamMonster, "monsters");
                    }
                    String playedMove;
                    // Each not fainted hero gets to play a move
                    for (Pawn pawn : teamHero.getPawnList()) {
                        if (((ValorHero) pawn).getDidFaint()) {
                            continue;
                        }
                        ValorHero hero = (ValorHero) pawn;
                        while (true) {
                            vLayout.displayLayout();
                            Utils.println("");
                            Utils.println(MsgType.INFO, "It is " + hero.getSymbol() + ": " + hero.getName() + "'s turn to play a move!");
                            System.out.println("Current Position : " + ((ValorCell) hero.getCurrentCell()).getLaneNumber()
                                    + " " + ((ValorCell) hero.getCurrentCell()).getLaneIndexX()
                                    + " " + ((ValorCell) hero.getCurrentCell()).getLaneIndexY());
                            Utils.println(MsgType.INFO, "Following are the rules for movements in the game!! Please press the right button to play the game!");
                            System.out.println("W/S/A/D - Move Up/Down/Left/Right");
                            System.out.println("E - Equip Item");
                            System.out.println("F - Attack");
                            System.out.println("G - Use Potion");
                            System.out.println("H - Use Spell");
                            System.out.println("R - Recall");
                            System.out.println("T - Teleport");
                            if (((ValorCell) hero.getCurrentCell()).getCellType() == CellSpace.HERONEXUS) {
                                System.out.println("M - Market");
                            }
                            System.out.println("Q - Quit Game");

                            try {
                                System.out.print("What would you like to do? ");
                                playedMove = Utils.input.readLine();
                                if (playedMove.equalsIgnoreCase("W") || playedMove.equalsIgnoreCase("S") || playedMove.equalsIgnoreCase("A") || playedMove.equalsIgnoreCase("D")) {
                                    if (handleBasicMove(hero, playedMove)) {
                                        break;
                                    } else {
                                        throw new Exception("Invalid Move");
                                    }
                                } else if (playedMove.equalsIgnoreCase("R")) {
                                    // Recall
                                    if (handleRecall(hero)) {
                                        break;
                                    } else {
                                        throw new Exception("Invalid Recall");
                                    }
                                } else if (playedMove.equalsIgnoreCase("T")) {
                                    // Teleport
                                    if (handleTeleport(hero)) {
                                        break;
                                    } else {
                                        throw new Exception("Invalid Teleport");
                                    }
                                } else if (playedMove.equalsIgnoreCase("F")) {
                                    // Attack
                                    if (handleAttack(hero)) {
                                        break;
                                    } else {
                                        throw new Exception("Invalid Attack");
                                    }
                                } else if (playedMove.equalsIgnoreCase("G")) {
                                    // Use potion
                                    if (BattleDriver.usePotion(hero)) {
                                        break;
                                    } else {
                                        throw new Exception("Failed to use potion");
                                    }
                                } else if (playedMove.equalsIgnoreCase("H")) {
                                    // Use spell
                                    if (handleSpell(hero)) {
                                        break;
                                    } else {
                                        throw new Exception("Failed to use spell");
                                    }
                                } else if (playedMove.equalsIgnoreCase("E")) {
                                    // Equip item
                                    if (handleEquip(hero)) {
                                        break;
                                    } else {
                                        throw new Exception("Failed to equip item");
                                    }
                                } else if (playedMove.equalsIgnoreCase("M") && ((ValorCell) hero.getCurrentCell()).getCellType() == CellSpace.HERONEXUS) {
                                    // Market
                                    MarketDriver.enterMarket(hero); // Entering the market won't end the turn
                                } else if (playedMove.equalsIgnoreCase("Q")) {
                                    Utils.println(MsgType.INFO, "You have quit the game! Thank you for playing!");
                                    break mainLoop;
                                } else {
                                    throw new Exception("Invalid Input! Please try again!");
                                }
                            } catch (Exception e) {
                                Utils.println(MsgType.ERROR, e.getMessage());
                            }
                        }
                        // Check if heroes win
                        if (((ValorCell) hero.getCurrentCell()).getLaneIndexX() == 0) {
                            Utils.println(MsgType.SUCCESS, "You have defeated the monster! You win!");
                            Utils.println(MsgType.INFO, "Do you want to play again? (Y/N)");
                            String playAgain = Utils.input.readLine();
                            if (playAgain.equalsIgnoreCase("Y")) {
                                playGame();
                            } else {
                                System.out.println("Thank you for playing!");
                                System.exit(0);
                            }
                        }
                    }
                    // For each monster to make a move if valid
                    for (Pawn pawn : teamMonster.getPawnList()) {
                        ValorMonster monster = (ValorMonster) pawn;
                        handleMonster(monster);
                    }
                    // Check and revive dead heroes
                    for (Pawn pawn : teamHero.getPawnList()) {
                        ValorHero hero = (ValorHero) pawn;
                        if (hero.getDidFaint()) {
                            hero.setFaint(false);
                            hero.setCurrentHitPoints(hero.getMaxHitPoints());
                            hero.setCurrentMagicPoints(hero.getMaxMagicPoints());
                            ValorCell originNexus = (ValorCell) hero.getNexusCell();
                            originNexus.setHero(hero);
                        }
                    }
                    currentRound++;
                }

            } catch (Exception error) {
                System.out.println("Uh oh!! The game crashed! Apologise for the inconvenience!! \nError " + error.getMessage());
            }

            System.out.println("I hope you saved your world and had fun while doing it ");
            System.out.print("Do you want to play again? (Y/N) ");
        } while (Utils.input.readLine().equalsIgnoreCase("Y"));
    }
}