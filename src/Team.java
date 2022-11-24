/**
 * Team Class: This class is used to create a team of pawns which would be required for the game.
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 20, 2022
 */

public class Team {

    private Pawn[] pawnList;
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
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        if (teamSize <= 0 ) {
            teamSize = 1;
        } else if (teamSize > 3) {
            teamSize = 3;
        }
        this.teamSize = teamSize;
    }

    public void initTeam() {
        pawnList = new Pawn[this.getTeamSize()];
    }

    public void setPawnAtIndex(Integer index, Pawn newTeamItem) {
        this.pawnList[index] = newTeamItem;
    }

    public Pawn getPawnAtIndex(Integer index) {
        return this.pawnList[index];
    }

    public Pawn[] getPawnList() {
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
}
