import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Utils {

    public static BufferedReader input;
    public static Random randomNumber;

    static {
        input = new BufferedReader(new InputStreamReader(System.in));
        randomNumber = new Random();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }
    public static void println(MsgType type, String message) {
        switch (type) {
            case INFO:
                System.out.println(TextColors.BLUE + "[INFO] " + TextColors.RESET +message);
                break;
            case SUCCESS:
                System.out.println(TextColors.GREEN + "[SUCCESS] " + TextColors.RESET +message);
                break;
            case WARNING:
                System.out.println(TextColors.YELLOW + "[WARNING] " + TextColors.RESET +message);
                break;
            case ERROR:
                System.out.println(TextColors.RED + "[ERROR] " + TextColors.RESET +message);
                break;
            case DEFAULT:
                System.out.println(message);
                break;
        }
    }

    public static void displayWeapons(ArrayList<Item> weaponList) {
        ValorDriver.printChar(TextColors.WHITE, '-', 102);
        System.out.println();
        Weapon.printHeaders();
        ValorDriver.printChar(TextColors.WHITE, '-', 102);
        System.out.println();

        for (int i = 0; i < weaponList.size(); i++) {
            ((Weapon) (weaponList.get(i))).printData(i + 1);
        }
        ValorDriver.printChar(TextColors.WHITE, '-', 102);
        System.out.println();
    }

    public static void displayArmors(ArrayList<Item> armorList) {
        ValorDriver.printChar(TextColors.WHITE, '-', 93);
        System.out.println();
        Armor.printHeaders();
        ValorDriver.printChar(TextColors.WHITE, '-', 93);
        System.out.println();

        for (int i = 0; i < armorList.size(); i++) {
            ((Armor) (armorList.get(i))).printData(i + 1);
        }
        ValorDriver.printChar(TextColors.WHITE, '-', 93);
        System.out.println();
    }

    public static void displayPotions(ArrayList<Item> potionList) {
        ValorDriver.printChar(TextColors.WHITE, '-', 110);
        System.out.println();
        Potion.printHeaders();
        ValorDriver.printChar(TextColors.WHITE, '-', 110);
        System.out.println();

        for (int i = 0; i < potionList.size(); i++) {
            ((Potion) (potionList.get(i))).printData(i + 1);
        }
        ValorDriver.printChar(TextColors.WHITE, '-', 110);
        System.out.println();
    }

    public static void displaySpells(ArrayList<Item> spellList) {
        ValorDriver.printChar(TextColors.WHITE, '-', 122);
        System.out.println();
        Spell.printHeaders();
        ValorDriver.printChar(TextColors.WHITE, '-', 122);
        System.out.println();

        for (int i = 0; i < spellList.size(); i++) {
            ((Spell) (spellList.get(i))).printData(i + 1);
        }
        ValorDriver.printChar(TextColors.WHITE, '-', 122);
        System.out.println();
    }

    public static void main(String[] args) {
        // Test print msg
        println(MsgType.INFO, "This is an info message");
        println(MsgType.SUCCESS, "This is a success message");
        println(MsgType.WARNING, "This is a warning message");
        println(MsgType.ERROR, "This is an error message");
        println(MsgType.DEFAULT, "This is a default message");
    }
}
