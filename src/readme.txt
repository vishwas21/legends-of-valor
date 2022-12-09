GRS CS 611 - Object-Oriented Design and Software Principles using JAVA

ASSIGNMENT 4 - LEGENDS OF VALOR

Submitted by:
VISHWAS BHAKTAVATSALA - vishwasb@bu.edu - U74206902
RUI WEI - rickwei@bu.edu - U02377614
-------------------------------------------------------------------------------------------------------------------------------------------------------------

Analysis of the two infrastructures:
+--------------------------------------------------------------------------------------------------------------------------------+
|                   | Bulletpoints                        | Design to keep?     | Design to modify?                              |
| ----------------- | ----------------------------------- | ------------------- | ---------------------------------------------- |
| Vishwas's Version | - Including audio function          | All other classes   | - Cell (more types, more effects)              |
|                   | - Clearer design                    |                     | - Board display (three lanes)                  |
|                   | - Factory pattern                   |                     | - Battle part (transform into on-board battle) |
|                   | - Cell color included               |                     |                                                |
| ----------------- | ----------------------------------- | ------------------- | ---------------------------------------------- |
| Ricky's Version   | - Modified rules                    | Multiple msg types  | - Cell (more types, more effects)              |
|                   | - Equipment slot                    |                     | - Rules (static methods to modify)             |
|                   |                                     |                     | - Equipment slot (not necessary)               |
+--------------------------------------------------------------------------------------------------------------------------------+

We decided to incorporate the base structure from Vishwas's project as it had individual classes for basic unit functionalities.
The board has each unit as a cell class and each cell object had various other attributes which helps to run the game better.
Using Enums, individual class helps the overall game run smoothly and adding new features becomes very easy.
The battle and move strategy was better designed by Rui's framework, as it had individual functions and classes for each feature of movement and outputs.
The structure used for individual movement made it easy to understand and incorporate with Vishwas's Framework.

-------------------------------------------------------------------------------------------------------------------------------------------------------------

Points to be considered for bonus:

1. Simple game structure which makes it each to understand the code.
2. Feature of sound which make the game lively.
3. Usage of Design Patterns such as Factory Pattern
4. Cute and Informative Output :)
5. Colorful Board and outputs

-------------------------------------------------------------------------------------------------------------------------------------------------------------

Object Oriented Design:

Here are the classes which we have used for the Legends of Valor Game:

1. Armor class: This class consists of functionalities with respect to armors, it's getters and setters usage in the game
2. BackgroundColors Class: This class stores the static variables for the background colors which are used in the board.
3. BattleDriver Class: This class has all the functions required for the battle to run, funstions to use the potions, spells and attack features as well.
4. Cell Class: This class depicts the basic unit in all the board or layout based games. It helps store the position and piece/character/pawn in the cell.
5. CellSpace Enum: This Enum stores the different type of cells which can be present in the Legends game and their respective Colors.
6. Item Abstract Class: This class holds the default details of all the items and its functionality. This is an abstract which can be extended by other games as well.
7. Item Factory : Factory Design pattern to create items. This class has the functions required to weapons, Armors, Potions and Spells.
8. ItemGenerator Class: This class is used as a driver to run the Item Factory Pattern. It creates all the items from the path and returns the objects in the form of a list.
9. ItemType Enum: This Enum is used to track all the ItemTypes which are present in this game.
10. Layout Interface: The interface helps all the games create a layout and set a few rules which must be followed.
11. Main Class: Entry point for the game, which calls the Legends of Valor game.
12. Market Driver: This is the driver class for the whole market in the game and has the functionalities to buy and sell items from the market.
13. MsgType Enum: This Enum is used to track the different types of messages in the game
14. NPCFactory Class: Design Pattern class used to produce Heroes and Monsters in the game.
15. NPCGenerator Class: This class has the functionality to create the heroes and monsters based on the files which is present in the path.
16. NPCType Enum: This Enum is used to show the different type of NPC Characters in the game
17. Pawn Class: This class represents the basic pawn and the characteristics which is required for it.
18. Potion Class: This class consists of functionalities with respect to potions and the required attributes.
19. PotionType Enum: This enum is used to depict the different types of Potions in the game.
20. Spell Class: This class consists of functionalities with respect to spells and the required attributes
21. SpellType Enum: This enum is used to depict the different types of Spells in the game.
22. Team Class: This class is used to create a team of pawns which would be required for the game, the functionalities required for each team and attributes.
23. TextColors Class: This class stores the static variables for the text colors which are used in the board.
24. Utils Class: This is a general utility class which has the common functionalities required all over the game.
25. ValorCell Class: This class inherits all the features of the Cell Super class and also store the type of the cell which is required for valor game
26. ValorDriver Class: This is the driver class for the Legends of Valor game. The Starting point for everything in this game.
27. ValorHero Class: This class depicts the hero character in the valor game and all the attribute for the hero character.
28. ValorLayout Class: The LegendsLayout class implements the Layout Interface and has Legends specific functionality.
29. ValorMonster Class: This class depicts the monster character in the valor game and all the attributes for the monster character.
30. ValorNPC Class: This class is inherited from the legends game which creates the non-playable character for the valor game.
31. VLayout Interface: This Interface is used to show the rules for the Valor game. This will inherit the rules from the Layout Interface.
32. Weapon Class: This class consists of functionalities with respect to weapons and its required attributes.

-------------------------------------------------------------------------------------------------------------------------------------------------------------
Steps to Compile and execute
-------------------------------------------------------------------------------------------------------------------------------------------------------------
1. Navigate to the directory "legends-of-valor" after unzipping the files
2. execute the following instructions:
<Example below>
javac -d bin src/*.java
java -cp bin Main


-------------------------------------------------------------------------------------------------------------------------------------------------------------
Example Output
-------------------------------------------------------------------------------------------------------------------------------------------------------------

$$\                                                    $$\                            $$$$$$\        $$\    $$\          $$\
$$ |                                                   $$ |                          $$  __$$\       $$ |   $$ |         $$ |
$$ |      $$$$$$\   $$$$$$\   $$$$$$\  $$$$$$$\   $$$$$$$ | $$$$$$$\        $$$$$$\  $$ /  \__|      $$ |   $$ |$$$$$$\  $$ | $$$$$$\   $$$$$$\
$$ |     $$  __$$\ $$  __$$\ $$  __$$\ $$  __$$\ $$  __$$ |$$  _____|      $$  __$$\ $$$$\           \$$\  $$  |\____$$\ $$ |$$  __$$\ $$  __$$\
$$ |     $$$$$$$$ |$$ /  $$ |$$$$$$$$ |$$ |  $$ |$$ /  $$ |\$$$$$$\        $$ /  $$ |$$  _|           \$$\$$  / $$$$$$$ |$$ |$$ /  $$ |$$ |  \__|
$$ |     $$   ____|$$ |  $$ |$$   ____|$$ |  $$ |$$ |  $$ | \____$$\       $$ |  $$ |$$ |              \$$$  / $$  __$$ |$$ |$$ |  $$ |$$ |
$$$$$$$$\\$$$$$$$\ \$$$$$$$ |\$$$$$$$\ $$ |  $$ |\$$$$$$$ |$$$$$$$  |      \$$$$$$  |$$ |               \$  /  \$$$$$$$ |$$ |\$$$$$$  |$$ |
\________|\_______| \____$$ | \_______|\__|  \__| \_______|\_______/        \______/ \__|                \_/    \_______|\__| \______/ \__|
                   $$\   $$ |
                   \$$$$$$  |
                    \______/
************************************************************ Legends of Valor************************************************************
[INFO] Welcome to Valor!!

[INFO] Valor is a turn based game where you have to reach the enemy nexus to win the game.

[INFO] 1. You can move your hero in the same lane using W/S/A/D.
[INFO] 2. Every cell can only have one hero. You cannot cross the monsters in the same lane.
[INFO] 3. You can teleport your hero to the adjacent cell of another hero.
[INFO] 4. You can attack the enemy in the cells around you.
[INFO] 5. You can cast your spells to attack the enemy in the cells around you.
[INFO] 6. You can use your items to strengthen yourself.
[INFO] 7. You can equip your items to strengthen yourself.
[INFO] 8. You can buy items from the market in the nexus.
[INFO] 9. Entering the market won't end your turn.
[INFO] 10. Every fainted hero will be revived in the nexus at the next round.
[INFO] 11. If the monsters reach the hero nexus, the game is over.
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+

    - Inaccessible Spaces
    - Hero's Nexus
    - Monster's Nexus
    - Plain Spaces
    - Cave Spaces
    - Bush Spaces
    - Koulou Spaces
 H  - Hero Team
[INFO] This is the layout of the map! Would you like to regenerate the map? (Y/N)
Input: n
[INFO] Let us build a team!
 There are multiple hero types. Please choose a number from the list shown
[INFO] Hero Number 1
[INFO] Please choose a number from the list of hero types:
1. Paladins
2. Sorcerers
3. Warriors
Input : 1
Here are the list of Paladins and their stats.
Enter the number associated with each hero to choose that hero in your team
--------------------------------------------------------------------------------------------------------------------------------------------------------
|  Sl. No.  |  Name                  |  Hit Points       |  Magic Power   |  Strength    |  Agility     |  Dexterity   |  Gold        |  Experience    |
--------------------------------------------------------------------------------------------------------------------------------------------------------
|  1        |  Parzival              |  100              |  300           |  750         |  650         |  700         |  2500        |  7             |
|  2        |  Sehanine_Moonbow      |  100              |  300           |  750         |  700         |  700         |  2500        |  7             |
|  3        |  Skoraeus_Stonebones   |  100              |  250           |  650         |  600         |  350         |  2500        |  4             |
|  4        |  Garl_Glittergold      |  100              |  100           |  600         |  500         |  400         |  2500        |  5             |
|  5        |  Amaryllis_Astra       |  100              |  500           |  500         |  500         |  500         |  2500        |  5             |
|  6        |  Caliber_Heist         |  100              |  400           |  400         |  400         |  400         |  2500        |  8             |
--------------------------------------------------------------------------------------------------------------------------------------------------------
[INFO] Please type the number of the hero :
1
[SUCCESS] Hero added successfully to your team!!


[INFO] Hero Number 2
[INFO] Please choose a number from the list of hero types:
1. Paladins
2. Sorcerers
3. Warriors
Input : 2
Here are the list of Sorcerers and their stats.
Enter the number associated with each hero to choose that hero in your team
--------------------------------------------------------------------------------------------------------------------------------------------------------
|  Sl. No.  |  Name                  |  Hit Points       |  Magic Power   |  Strength    |  Agility     |  Dexterity   |  Gold        |  Experience    |
--------------------------------------------------------------------------------------------------------------------------------------------------------
|  1        |  Rillifane_Rallathil   |  100              |  1300          |  750         |  450         |  500         |  2500        |  9             |
|  2        |  Segojan_Earthcaller   |  100              |  900           |  800         |  500         |  650         |  2500        |  5             |
|  3        |  Reign_Havoc           |  100              |  800           |  800         |  800         |  800         |  2500        |  8             |
|  4        |  Reverie_Ashels        |  100              |  900           |  800         |  700         |  400         |  2500        |  7             |
|  5        |  Kalabar               |  100              |  800           |  850         |  400         |  600         |  2500        |  6             |
|  6        |  Skye_Soar             |  100              |  1000          |  700         |  400         |  500         |  2500        |  5             |
--------------------------------------------------------------------------------------------------------------------------------------------------------
[INFO] Please type the number of the hero :
2
[SUCCESS] Hero added successfully to your team!!


[INFO] Hero Number 3
[INFO] Please choose a number from the list of hero types:
1. Paladins
2. Sorcerers
3. Warriors
Input : 3
Here are the list of Warriors and their stats.
Enter the number associated with each hero to choose that hero in your team
--------------------------------------------------------------------------------------------------------------------------------------------------------
|  Sl. No.  |  Name                  |  Hit Points       |  Magic Power   |  Strength    |  Agility     |  Dexterity   |  Gold        |  Experience    |
--------------------------------------------------------------------------------------------------------------------------------------------------------
|  1        |  Gaerdal_Ironhand      |  100              |  100           |  700         |  500         |  600         |  1354        |  7             |
|  2        |  Sehanine_Monnbow      |  100              |  600           |  700         |  800         |  500         |  2500        |  8             |
|  3        |  Muamman_Duathall      |  100              |  300           |  900         |  500         |  750         |  2546        |  6             |
|  4        |  Flandal_Steelskin     |  100              |  200           |  750         |  650         |  700         |  2500        |  7             |
|  5        |  Undefeated_Yoj        |  100              |  400           |  800         |  400         |  700         |  2500        |  7             |
|  6        |  Eunoia_Cyn            |  100              |  400           |  700         |  800         |  600         |  2500        |  6             |
--------------------------------------------------------------------------------------------------------------------------------------------------------
[INFO] Please type the number of the hero :
3
[SUCCESS] Hero added successfully to your team!!


[INFO] This is the team of Heroes :
--------------------------------------------------------------------------------------------------------------------------------------------------------
|  Sl. No.  |  Name                  |  Hit Points       |  Magic Power   |  Strength    |  Agility     |  Dexterity   |  Gold        |  Experience    |
--------------------------------------------------------------------------------------------------------------------------------------------------------
|  1        |  Parzival              |  100              |  300           |  750         |  650         |  700         |  2500        |  7             |
|  2        |  Segojan_Earthcaller   |  100              |  900           |  800         |  500         |  650         |  2500        |  5             |
|  3        |  Muamman_Duathall      |  100              |  300           |  900         |  500         |  750         |  2546        |  6             |
--------------------------------------------------------------------------------------------------------------------------------------------------------
Hit Enter to continue :)





+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|   H1            |                 |                 |   H2            |                 |                 |   H3            |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+

    - Inaccessible Spaces
    - Hero's Nexus
    - Monster's Nexus
    - Plain Spaces
    - Cave Spaces
    - Bush Spaces
    - Koulou Spaces
 H  - Hero Team
[INFO] This is the latest state of the layout with the heroes team present :)
Hit Enter to continue :)

[INFO] Round 1 :


 Below are the team of Monsters :
--------------------------------------------------------------------------------------------------------------------
|  Sl. No.  |  Name                  |  Level     |  Hit Points  |  Damage           |  Defense     |  Dodge       |
--------------------------------------------------------------------------------------------------------------------
|  1        |  BigBad-Wolf           |  1         |  100         |  150              |  250         |  15          |
|  2        |  Natsunomeryu          |  1         |  100         |  100              |  200         |  10          |
|  3        |  Natsunomeryu          |  1         |  100         |  100              |  200         |  10          |
--------------------------------------------------------------------------------------------------------------------
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |           M1    |                 |                 |           M2    |                 |                 |           M3    |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|   H1            |                 |                 |   H2            |                 |                 |   H3            |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+

    - Inaccessible Spaces
    - Hero's Nexus
    - Monster's Nexus
    - Plain Spaces
    - Cave Spaces
    - Bush Spaces
    - Koulou Spaces
 H  - Hero Team

[INFO] It is H1: Parzival's turn to play a move!
Current Position : 0 7 0
[INFO] Following are the rules for movements in the game!! Please press the right button to play the game!
W/S/A/D - Move Up/Down/Left/Right
E - Equip Item
F - Attack
G - Use Potion
H - Use Spell
R - Recall
T - Teleport
M - Market
Q - Quit Game
What would you like to do? W
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |           M1    |                 |                 |           M2    |                 |                 |           M3    |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|   H1            |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |   H2            |                 |                 |   H3            |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+

    - Inaccessible Spaces
    - Hero's Nexus
    - Monster's Nexus
    - Plain Spaces
    - Cave Spaces
    - Bush Spaces
    - Koulou Spaces
 H  - Hero Team

[INFO] It is H2: Segojan_Earthcaller's turn to play a move!
Current Position : 1 7 0
[INFO] Following are the rules for movements in the game!! Please press the right button to play the game!
W/S/A/D - Move Up/Down/Left/Right
E - Equip Item
F - Attack
G - Use Potion
H - Use Spell
R - Recall
T - Teleport
M - Market
Q - Quit Game
What would you like to do? W
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |           M1    |                 |                 |           M2    |                 |                 |           M3    |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|   H1            |                 |                 |   H2            |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |   H3            |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+

    - Inaccessible Spaces
    - Hero's Nexus
    - Monster's Nexus
    - Plain Spaces
    - Cave Spaces
    - Bush Spaces
    - Koulou Spaces
 H  - Hero Team

[INFO] It is H3: Muamman_Duathall's turn to play a move!
Current Position : 2 7 0
[INFO] Following are the rules for movements in the game!! Please press the right button to play the game!
W/S/A/D - Move Up/Down/Left/Right
E - Equip Item
F - Attack
G - Use Potion
H - Use Spell
R - Recall
T - Teleport
M - Market
Q - Quit Game
What would you like to do? W
[INFO] Round 2 :
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |           M1    |                 |                 |           M2    |                 |                 |           M3    |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|   H1            |                 |                 |   H2            |                 |                 |   H3            |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+

    - Inaccessible Spaces
    - Hero's Nexus
    - Monster's Nexus
    - Plain Spaces
    - Cave Spaces
    - Bush Spaces
    - Koulou Spaces
 H  - Hero Team

[INFO] It is H1: Parzival's turn to play a move!
Current Position : 0 6 0
[INFO] Following are the rules for movements in the game!! Please press the right button to play the game!
W/S/A/D - Move Up/Down/Left/Right
E - Equip Item
F - Attack
G - Use Potion
H - Use Spell
R - Recall
T - Teleport
Q - Quit Game
What would you like to do? W
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |           M1    |                 |                 |           M2    |                 |                 |           M3    |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|   H1            |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |   H2            |                 |                 |   H3            |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+

    - Inaccessible Spaces
    - Hero's Nexus
    - Monster's Nexus
    - Plain Spaces
    - Cave Spaces
    - Bush Spaces
    - Koulou Spaces
 H  - Hero Team

[INFO] It is H2: Segojan_Earthcaller's turn to play a move!
Current Position : 1 6 0
[INFO] Following are the rules for movements in the game!! Please press the right button to play the game!
W/S/A/D - Move Up/Down/Left/Right
E - Equip Item
F - Attack
G - Use Potion
H - Use Spell
R - Recall
T - Teleport
Q - Quit Game
What would you like to do? D
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |           M1    |                 |                 |           M2    |                 |                 |           M3    |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|   H1            |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |   H2            |                 |   H3            |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+

    - Inaccessible Spaces
    - Hero's Nexus
    - Monster's Nexus
    - Plain Spaces
    - Cave Spaces
    - Bush Spaces
    - Koulou Spaces
 H  - Hero Team

[INFO] It is H3: Muamman_Duathall's turn to play a move!
Current Position : 2 6 0
[INFO] Following are the rules for movements in the game!! Please press the right button to play the game!
W/S/A/D - Move Up/Down/Left/Right
E - Equip Item
F - Attack
G - Use Potion
H - Use Spell
R - Recall
T - Teleport
Q - Quit Game
What would you like to do? W
[INFO] Round 3 :
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |           M1    |                 |                 |           M2    |                 |                 |           M3    |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|   H1            |                 |                 |                 |                 |                 |   H3            |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |   H2            |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
|                 |                 |                 |                 |                 |                 |                 |                 |
+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+

    - Inaccessible Spaces
    - Hero's Nexus
    - Monster's Nexus
    - Plain Spaces
    - Cave Spaces
    - Bush Spaces
    - Koulou Spaces
 H  - Hero Team

[INFO] It is H1: Parzival's turn to play a move!
Current Position : 0 5 0
[INFO] Following are the rules for movements in the game!! Please press the right button to play the game!
W/S/A/D - Move Up/Down/Left/Right
E - Equip Item
F - Attack
G - Use Potion
H - Use Spell
R - Recall
T - Teleport
Q - Quit Game
What would you like to do?