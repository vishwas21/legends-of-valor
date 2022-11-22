/**
 * CellSpace Enum: This Enum stores the different type of cells which can be present in the Legends game and their respective Color and Symbol.
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 20, 2022
 */

import java.util.HashMap;

public enum CellSpace {
    INACCESSIBLE,
    HERONEXUS,
    MONSTERNEXUS,
    PLAIN,
    BUSH,
    CAVE,
    KOULOU;


    public static HashMap<CellSpace, String> spaceColor;
    public static HashMap<CellSpace, String> spaceSymbol;

    static {
        spaceColor = new HashMap<>(3);
        spaceColor.put(CellSpace.INACCESSIBLE, BackgroundColors.BLACK);
        spaceColor.put(CellSpace.HERONEXUS, BackgroundColors.BLUE);
        spaceColor.put(CellSpace.MONSTERNEXUS, BackgroundColors.RED);
        spaceColor.put(CellSpace.PLAIN, BackgroundColors.CYAN);
        spaceColor.put(CellSpace.BUSH, BackgroundColors.GREEN);
        spaceColor.put(CellSpace.CAVE, BackgroundColors.YELLOW);
        spaceColor.put(CellSpace.KOULOU, BackgroundColors.PURPLE);

//        spaceSymbol = new HashMap<>(3);
//        spaceSymbol.put(CellSpace.INACCESSIBLE, "X");
//        spaceSymbol.put(CellSpace.MARKET, "M");
//        spaceSymbol.put(CellSpace.COMMON, " ");
    }
}
