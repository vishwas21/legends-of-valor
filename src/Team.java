/**
 * Team Class: This class is used to create a team of pawns which would be required for the game, the functionalities required for each team and attributes.
 */

import java.util.ArrayList;

public class Team {

    private ArrayList<Pawn> pawnList;
    private Integer teamSize;
    private Integer positionX;
    private Integer positionY;

    public Team() {
        positionX = 0;
        positionY = 0;
    }

    public Team(Integer teamSize) {
        this.setTeamSize(teamSize);
    }

    public Integer getTeamSize() {
        return this.teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }

    public void increaseTeamSize(Integer incrementSize) {
        this.teamSize += incrementSize;
    }

    public void initTeam() {
        pawnList = new ArrayList<>();
    }

    public void setPawnAtIndex(Integer index, Pawn newTeamItem) {
        this.pawnList.add(index, newTeamItem);
    }

    public Pawn getPawnAtIndex(Integer index) {
        return this.pawnList.get(index);
    }

    public void addPawn(Pawn newTeamItem) {
        this.pawnList.add(newTeamItem);
    }

    public void removePawn(Pawn newTeamItem) {
        this.pawnList.remove(newTeamItem);
    }

    public ArrayList<Pawn> getPawnList() {
        return pawnList;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

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
        for (int index = 0; index < team.getPawnList().size(); index++) {
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

    public Team clone() {
        Team newTeam = new Team(this.getTeamSize());
        newTeam.initTeam();
        for (Pawn pawn : this.getPawnList()) {
            ValorHero hero = (ValorHero) pawn;
            newTeam.addPawn(hero);
        }
        return newTeam;
    }
}
