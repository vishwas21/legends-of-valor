import java.util.ArrayList;

/**
 * Team Class: This class is used to create a team of pawns which would be required for the game.
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 20, 2022
 */

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

    public Pawn removePawn(Pawn newTeamItem) {
        this.pawnList.remove(newTeamItem);
        this.teamSize--;
        return newTeamItem;
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
