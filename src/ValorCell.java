/**
 * ValorCell Class: This class inherits all the features of the Cell Super class and also store the type of the cell which is required for valor game
 */

public class ValorCell extends Cell {

    private CellSpace cellType;
    private Integer laneNumber;
    private Integer laneIndexX;
    private Integer laneIndexY;
    private Pawn hero;
    private Pawn monster;
    private boolean discovered;

    public ValorCell(Integer unitPosition, Integer unitIndexX, Integer unitIndexY) {
        super(unitPosition, unitIndexX, unitIndexY);
        this.setCellType(CellSpace.DEFAULT);
        int lane = (unitIndexY < 2) ? 0 : ((unitIndexY < 5) ? 1 : 2);
        this.setLaneNumber(lane);
        this.setLaneIndexX(unitIndexX);
        this.setLaneIndexY(unitIndexY - 3 * lane);
        this.setHero(null);
        this.setMonster(null);
        this.setDiscovered(false);
    }

    public void setDiscovered(boolean b) {
        this.discovered = b;
    }

    public boolean isDiscovered() {
        return this.discovered;
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
