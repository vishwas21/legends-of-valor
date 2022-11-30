/**
 * NPCGenerator Class: This class has the functionality to create the heroes and monsters based on the files which is present in the path.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NPCGenerator {
    public static ArrayList<Pawn> generatePawn(NPCType pawnType, ArrayList<Pawn> npcList, String nameOfFile) throws IOException {
        try(FileReader file = new FileReader("" + System.getProperty("user.dir") + "/src/constants/" + nameOfFile);
            BufferedReader reader = new BufferedReader(file);) {
            String singleLine = "";
            NPCFactory factory = new NPCFactory();
            Pawn tempObj;
            String[] inputArray;
            reader.readLine();

            while (true) {
                singleLine = reader.readLine();
                if (singleLine == null) {
                    break;
                }
                tempObj = factory.createObject(pawnType);
                inputArray = singleLine.split("/");
                if (pawnType == NPCType.HERO) {
                    ((ValorHero)tempObj).init(inputArray[0], 1, 100, Integer.parseInt(inputArray[6]),
                            Integer.parseInt(inputArray[1]), Integer.parseInt(inputArray[2]), Integer.parseInt(inputArray[4]),
                            Integer.parseInt(inputArray[3]), Integer.parseInt(inputArray[5]));
                } else if (pawnType == NPCType.MONSTER) {
                    ((ValorMonster)tempObj).init(inputArray[0], Integer.parseInt(inputArray[1]),
                            Integer.parseInt(inputArray[1]) * 100, Integer.parseInt(inputArray[2]),
                            Integer.parseInt(inputArray[3]), Integer.parseInt(inputArray[4]));
                }
                npcList.add(tempObj);
            }

            return npcList;
        }
    }
}
