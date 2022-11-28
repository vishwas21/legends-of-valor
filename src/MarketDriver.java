import java.io.IOException;

/**
 * Market Driver: This is the driver class for the whole market in the game and has the functionalities to buy items from the market.
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 5, 2022
 */

public class MarketDriver {

    private static Integer customerHero;

    public static void weaponDriver() throws IOException {

        // TODO: Check item level before allowing to buy
        System.out.println("Here are the list of weapons which you can buy from");
        Utils.displayWeapons(ValorDriver.getMarketItemMap().get(ItemType.WEAPON));

        System.out.print("Please select a Weapon which you would like to buy: ");
        int itemNumber = Integer.parseInt(Utils.input.readLine()) - 1;

        if (itemNumber < 0 || itemNumber >= ValorDriver.getMarketItemMap().get(ItemType.WEAPON).size()) {
            throw new IllegalStateException("Invalid Item selected");
        }

        if (((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getLevel() < ValorDriver.getMarketItemMap().get(ItemType.WEAPON).get(itemNumber).getLevel()) {
            System.out.println("Hero level is not high enough to buy this item!! Please try again");
            return;
        }

        int weaponPrice = ValorDriver.getMarketItemMap().get(ItemType.WEAPON).get(itemNumber).getPrice();

        if (((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() < weaponPrice) {
            System.out.println("The hero you have chosen does not have enough gold to buy the weapon you have chosen!");
            return;
        }

        ((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).setGold(((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() - weaponPrice);
        ((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getItemInventory().get(ItemType.WEAPON).add(((Weapon)(ValorDriver.getMarketItemMap().get(ItemType.WEAPON).get(itemNumber))).cloneObj());
    }

    public static void armorDriver() throws IOException {
        System.out.println("Here are the list of Armors which you can buy from");
        Utils.displayArmors(ValorDriver.getMarketItemMap().get(ItemType.ARMOR));

        System.out.print("Please select a Armor which you would like to buy: ");
        int itemNumber = Integer.parseInt(Utils.input.readLine()) - 1;

        if (itemNumber < 0 || itemNumber >= ValorDriver.getMarketItemMap().get(ItemType.ARMOR).size()) {
            throw new IllegalStateException("Invalid Item selected");
        }

        if (((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getLevel() < ValorDriver.getMarketItemMap().get(ItemType.ARMOR).get(itemNumber).getLevel()) {
            System.out.println("Hero level is not high enough to buy this item!! Please try again");
            return;
        }

        int armorPrice = ValorDriver.getMarketItemMap().get(ItemType.ARMOR).get(itemNumber).getPrice();

        if (((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() < armorPrice) {
            System.out.println("The hero you have chosen does not have enough gold to buy the armor you have chosen!");
            return;
        }

        ((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).setGold(((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() - armorPrice);
        ((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getItemInventory().get(ItemType.ARMOR).add(((Armor)(ValorDriver.getMarketItemMap().get(ItemType.ARMOR).get(itemNumber))).cloneObj());
    }

    public static void potionsDriver() throws IOException {
        System.out.println("Here are the list of potions which you can buy from");
        Utils.displayPotions(ValorDriver.getMarketItemMap().get(ItemType.POTION));

        System.out.print("Please select a potion which you would like to buy: ");
        int itemNumber = Integer.parseInt(Utils.input.readLine()) - 1;

        if (itemNumber < 0 || itemNumber >= ValorDriver.getMarketItemMap().get(ItemType.POTION).size()) {
            throw new IllegalStateException("Invalid Item selected");
        }

        int potionsPrice = ValorDriver.getMarketItemMap().get(ItemType.POTION).get(itemNumber).getPrice();

        if (((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getLevel() < ValorDriver.getMarketItemMap().get(ItemType.POTION).get(itemNumber).getLevel()) {
            System.out.println("Hero level is not high enough to buy this item!! Please try again");
            return;
        }

        if (((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() < potionsPrice) {
            System.out.println("The hero you have chosen does not have enough gold to buy the potion you have chosen!");
            return;
        }

        ((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).setGold(((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() - potionsPrice);
        ((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getItemInventory().get(ItemType.POTION).add(((Potion)(ValorDriver.getMarketItemMap().get(ItemType.POTION).get(itemNumber))).cloneObj());
    }

    public static void spellDriver() throws IOException {
        System.out.println("Here are the list of spells which you can buy from");
        Utils.displaySpells(ValorDriver.getMarketItemMap().get(ItemType.SPELL));

        System.out.print("Please select a spell which you would like to buy: ");
        int itemNumber = Integer.parseInt(Utils.input.readLine()) - 1;

        if (itemNumber < 0 || itemNumber >= ValorDriver.getMarketItemMap().get(ItemType.SPELL).size()) {
            throw new IllegalStateException("Invalid Item selected");
        }

        if (((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getLevel() < ValorDriver.getMarketItemMap().get(ItemType.SPELL).get(itemNumber).getLevel()) {
            System.out.println("Hero level is not high enough to buy this weapon!! Please try again");
            return;
        }

        int spellPrice = ValorDriver.getMarketItemMap().get(ItemType.SPELL).get(itemNumber).getPrice();

        if (((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() < spellPrice) {
            System.out.println("The hero you have chosen does not have enough gold to buy the spell you have chosen!");
            return;
        }

        ((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).setGold(((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() - spellPrice);
        ((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero))).getItemInventory().get(ItemType.SPELL).add(((Spell)(ValorDriver.getMarketItemMap().get(ItemType.SPELL).get(itemNumber))).cloneObj());
    }

    public static void saleDriver(ValorHero selectedHero, ItemType itemType) throws IOException {
        if (selectedHero.getItemInventory().get(itemType).size() == 0) {
            System.out.println("Hero does not have any " + itemType + " to sell!");
        } else {
            System.out.println("Below are the " + itemType.toString() + " which the hero has to sell : ");
            if (itemType == ItemType.WEAPON) {
                Utils.displayWeapons(selectedHero.getItemInventory().get(itemType));
            } else if (itemType == ItemType.ARMOR) {
                Utils.displayArmors(selectedHero.getItemInventory().get(itemType));
            } else if (itemType == ItemType.POTION) {
                Utils.displayPotions(selectedHero.getItemInventory().get(itemType));
            } else if (itemType == ItemType.SPELL) {
                Utils.displaySpells(selectedHero.getItemInventory().get(itemType));
            }

            System.out.print("Please select a "+ itemType +" which you would like to sell: ");
            int itemNumber = Integer.parseInt(Utils.input.readLine()) - 1;

            if (itemNumber < 0 || itemNumber >= selectedHero.getItemInventory().get(itemType).size()) {
                throw new IllegalStateException("Invalid Item selected");
            }

            int itemPrice = selectedHero.getItemInventory().get(itemType).get(itemNumber).getPrice() / 2;

            System.out.println("This item can be sold for " + itemPrice + " Gold. \nWould you like to continue? (Y/any)");
            if (Utils.input.readLine().equalsIgnoreCase("Y")) {
                selectedHero.setGold(selectedHero.getGold() + itemPrice);
                selectedHero.getItemInventory().get(itemType).remove(itemNumber);
                System.out.println("Sale Successful!! Item has been sold for "+ itemPrice + " Gold.");
            } else {
                System.out.println("Sale aborted :(");
            }
        }
    }

    public static void enterMarket() throws IOException {
        while (true) {
            ValorDriver.printChar(TextColors.PURPLE, '*', 160);
            System.out.println();
            System.out.println("Welcome to the market! Here are various items which you can use to fight the monsters!\nHappy shopping :D\n");
            Team.displayTeam(ValorDriver.getTeamHero(), "Heroes");

            try {
                System.out.print("Please choose the hero who you want to buy/sell the items : ");
                customerHero = Integer.parseInt(Utils.input.readLine()) - 1;

                if (customerHero < 0 || customerHero >= ValorDriver.getTeamHero().getTeamSize()) {
                    throw new IllegalStateException("Invalid hero choice");
                }
                System.out.println();
                System.out.println("Please choose one of the below options: \n1. Buy Items\n2. Sell Items");
                int marketChoice = Integer.parseInt(Utils.input.readLine());
                if (marketChoice != 1 && marketChoice != 2) {
                    throw new IllegalStateException("Invalid choice!! \nYou and either buy or sell!! Please retry!");
                }

                System.out.println("\n\n1. Weapons\n2. Armors\n3. Potions\n4. Spells");
                System.out.print("Please enter the number of the item which you would like to buy: ");
                int choice = Integer.parseInt(Utils.input.readLine());

                if (marketChoice == 1) {

                    if (choice == 1) {
                        weaponDriver();
                    } else if (choice == 2) {
                        armorDriver();
                    } else if (choice == 3) {
                        potionsDriver();
                    } else if (choice == 4) {
                        spellDriver();
                    } else {
                        System.out.println("Hope you can choose the right option next time! Please try again! :)");
                    }
                } else {
                    ValorHero selectedHero = ((ValorHero)(ValorDriver.getTeamHero().getPawnAtIndex(customerHero)));
                    if (choice == 1) {
                        saleDriver(selectedHero, ItemType.WEAPON);
                    } else if (choice == 2) {
                        saleDriver(selectedHero, ItemType.ARMOR);
                    } else if (choice == 3) {
                        saleDriver(selectedHero, ItemType.POTION);
                    } else if (choice == 4) {
                        saleDriver(selectedHero, ItemType.SPELL);
                    } else {
                        System.out.println("Hope you can choose the right option next time! Please try again! :)");
                    }
                }
            } catch (Exception error) {
                System.out.println("Error Encountered while selecting items : " + error.getMessage());
                System.out.println("Please Try again!!");
                continue;
            }
            System.out.print("Would you like to continue (Y/N): ");
            if ((Utils.input.readLine()).equalsIgnoreCase("N")) {
                break;
            }
        }
    }
}
