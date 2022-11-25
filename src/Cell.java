/**
 * Cell Class: This class depicts the basic unit in all of the board or layout based games. It helps store the position and piece/character/pawn in the cell.
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 20, 2022
 */

public class Cell {
    private Integer unitPosition;
    private Integer unitPositionX;
    private Integer unitPositionY;
    private LegendsHero hero;
    private LegendsMonster monster;


    public Cell(Integer unitPosition, Integer unitPositionX, Integer unitPositionY) {
        this.setUnitPosition(unitPosition);
        this.setUnitPositionX(unitPositionX);
        this.setUnitPositionY(unitPositionY);
    }

    public void setUnitPosition(Integer unitPosition) {
        this.unitPosition = unitPosition;
    }

    public Integer getUnitPosition() {
        return this.unitPosition;
    }

    public void setUnitPositionX(Integer unitPositionX) {
        this.unitPositionX = unitPositionX;
    }

    public Integer getUnitPositionX() {
        return this.unitPositionX;
    }

    public void setUnitPositionY(Integer unitPositionY) {
        this.unitPositionY = unitPositionY;
    }

    public Integer getUnitPositionY() {
        return this.unitPositionY;
    }

    public void placeHero(LegendsHero hero) {
        this.hero = hero;
    }

    public void placeMonster(LegendsMonster monster) {
        this.monster = monster;
    }

    public void removeHero(){
        this.hero = null;
    }

    public void removeMonster(){
        this.monster = null;
    }

    public LegendsHero getHero() { return this.hero; }

    public LegendsMonster getMonster() { return this.monster; }

    public Boolean isHeroPresent() {
        if (this.hero == null) {
            return false;
        }
        return true;
    }

    public Boolean isMonsterPresent() {
        if (this.monster == null) {
            return false;
        }
        return true;
    }
}
