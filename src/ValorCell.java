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
    private Integer laneIndex;

    public ValorCell(Integer unitPosition, Integer unitIndexX, Integer unitIndexY) {
        super(unitPosition, unitIndexX, unitIndexY);
        this.setCellType(CellSpace.PLAIN);
//        this.setLaneNumber(laneNumber);
//        this.setLaneIndex(laneIndex);
    }

    public Integer getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(Integer laneNumber) {
        this.laneNumber = laneNumber;
    }

    public Integer getLaneIndex() {
        return laneIndex;
    }

    public void setLaneIndex(Integer laneIndex) {
        this.laneIndex = laneIndex;
    }

    public CellSpace getCellType() {
        return cellType;
    }

    public void setCellType(CellSpace cellType) {
        this.cellType = cellType;
    }
}
