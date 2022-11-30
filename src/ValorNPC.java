/**
 * ValorNPC Class: This class is inherited from the legends game which creates the non-playable character for the valor game.
 *                  This would be inherited by the individual valor hero and monster class
 */


public abstract class ValorNPC extends Pawn {

    private Integer level;
    private Integer maxHitPoints;
    private Integer currentHitPoints;
    private Cell nexusCell;
    private Cell currentCell;

    protected ValorNPC() {}

    protected ValorNPC(String name, Integer level, Integer maxHitPoints) {
        super(name);
        this.setLevel(level);
        this.setMaxHitPoints(maxHitPoints);
        this.setCurrentHitPoints(maxHitPoints);
    }

    public void init(String name, Integer level, Integer maxHitPoints) {
        super.init(name);
        this.setLevel(level);
        this.setMaxHitPoints(maxHitPoints);
        this.setCurrentHitPoints(maxHitPoints);
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) throws IllegalStateException {
        if (level <= 0) {
            throw new IllegalStateException("Level of a character cannot be lesser than 1");
        }
        this.level = level;
    }

    public Integer getMaxHitPoints() {
        return maxHitPoints;
    }

    public void setMaxHitPoints(Integer maxHitPoints) throws IllegalStateException {
        if (maxHitPoints < 0) {
            throw new IllegalStateException("Maximum Health Points cannot be lesser than 0!!");
        }

        this.maxHitPoints = maxHitPoints;
    }

    public Integer getCurrentHitPoints() {
        return currentHitPoints;
    }

    public void setCurrentHitPoints(Integer currentHitPoints) {
        if (currentHitPoints < 0) {
            currentHitPoints = 0;
        }

        this.currentHitPoints = currentHitPoints;
    }

    public void increaseHitPoints(Integer healthPoints) throws IllegalStateException {
        if (healthPoints <= 0) {
            throw new IllegalStateException("The health points to be increased cannot be in negative!");
        }

        this.currentHitPoints += healthPoints;
    }

    public void decreaseHitPoints(Integer hitPoints) {
        if (hitPoints <= 0) {
            throw new IllegalStateException("The health points to be decreased cannot be in negative!");
        }

        if (hitPoints > this.currentHitPoints) {
            this.currentHitPoints = 0;
        } else {
            this.currentHitPoints -= hitPoints;
        }
    }

    public Cell getNexusCell() {
        return nexusCell;
    }

    public void setNexusCell(Cell nexusCell) {
        this.nexusCell = nexusCell;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }
}
