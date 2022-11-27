/**
 * ValorCell Class: This class inherits all the features of the Cell Super class and also store the type of the cell which is required for valor game
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 20, 2022
 */

public class ValorCell extends Cell {

    private CellSpace cellType;
    private Integer laneNumber;
    private Integer laneIndexX;
    private Integer laneIndexY;
    private Pawn hero;
    private Pawn monster;

    public ValorCell(Integer unitPosition, Integer unitIndexX, Integer unitIndexY) {
        super(unitPosition, unitIndexX, unitIndexY);
        this.setCellType(CellSpace.DEFAULT);
        this.setLaneNumber(-1);
        this.setLaneIndexX(-1);
        this.setLaneIndexY(-1);
        this.setHero(null);
        this.setMonster(null);
    }

    public CellSpace getCellType() {
        return cellType;
    }

    public void setCellType(CellSpace cellType) {
        this.cellType = cellType;
    }

    public Integer getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(Integer laneNumber) {
        this.laneNumber = laneNumber;
    }

    public Integer getLaneIndexX() {
        return laneIndexX;
    }

    public void setLaneIndexX(Integer laneIndexX) {
        this.laneIndexX = laneIndexX;
    }

    public Integer getLaneIndexY() {
        return laneIndexY;
    }

    public void setLaneIndexY(Integer laneIndexY) {
        this.laneIndexY = laneIndexY;
    }

    public Pawn getHero() {
        return hero;
    }

    public void setHero(Pawn hero) {
        this.hero = hero;
    }

    public Pawn getMonster() {
        return monster;
    }

    public void setMonster(Pawn monster) {
        this.monster = monster;
    }

    public void removeHero() {
        this.hero = null;
    }

    public void removeMonster() {
        this.monster = null;
    }

    public boolean isHeroPresent() {
        return this.hero != null;
    }

    public boolean isMonsterPresent() {
        return this.monster != null;
    }
}
