GRS CS 611 - Object-Oriented Design and Software Principles using JAVA

ASSIGNMENT 4 - LEGENDS OF VALOR

Submitted by:
VISHWAS BHAKTAVATSALA - U74206902
RUI WEI - U02377614
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


