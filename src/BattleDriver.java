public class BattleDriver {
    public static void displayTeam(Team team, String teamType) {
        Pawn pawn;
        if (teamType.equalsIgnoreCase("Heroes")) {
            ValorDriver.printChar(TextColors.WHITE, '-', 152);
            System.out.println();
            ValorHero.printHeaders();
            ValorDriver.printChar(TextColors.WHITE, '-', 152);
            System.out.println();
        } else {
            ValorDriver.printChar(TextColors.WHITE, '-', 116);
            System.out.println();
            ValorMonster.printHeaders();
            ValorDriver.printChar(TextColors.WHITE, '-', 116);
            System.out.println();
        }
        for (int index = 0; index < team.getTeamSize(); index++) {
            pawn = team.getPawnAtIndex(index);
            if (teamType.equalsIgnoreCase("Heroes")) {
                if (!((ValorHero) pawn).getDidFaint()) {
                    ((ValorHero) pawn).printData(index + 1);
                }
            } else {
                if (pawn != null) {
                    ((ValorMonster) pawn).printData(index + 1);
                }
            }
        }
        if (teamType.equalsIgnoreCase("Heroes")) {
            ValorDriver.printChar(TextColors.WHITE, '-', 152);
            System.out.println();
        } else {
            ValorDriver.printChar(TextColors.WHITE, '-', 116);
            System.out.println();
        }
    }
}
