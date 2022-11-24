/**
 * LegendsLayout Class: The LegendsLayout class implements the Layout Interface and has Legends specific functionality.
 *      This class instantiates the layout and also add the inaccessible and market sport for the legends game layout
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 20, 2022
 */


import java.util.Random;

public class ValorLayout implements Layout {

    private Integer length;
    private Integer breadth;
    private Integer caveCellPercent;
    private Integer bushCellPercent;
    private Integer koulouCellPercent;


    private Cell[][] gameLayout;

    public ValorLayout(Integer squareSide) {
        this(squareSide, squareSide);
    }

    public ValorLayout(Integer rectangleLength, Integer rectangleBreadth) {
        this.setLength(rectangleLength);
        this.setBreadth(rectangleBreadth);
        this.setCaveCellPercent(25);
        this.setBushCellPercent(25);
        this.setKoulouCellPercent(25);
    }

    @Override
    public Integer getLength() {
        return length;
    }

    @Override
    public void setLength(Integer length) throws IllegalStateException {
        if (length > 0) {
            this.length = length;
        } else {
            throw new IllegalStateException("Invalid dimension for length of the layout");
        }
    }

    @Override
    public Integer getBreadth() {
        return breadth;
    }

    @Override
    public void setBreadth(Integer breadth) throws IllegalStateException {
        if (breadth > 0) {
            this.breadth = breadth;
        } else {
            throw new IllegalStateException("Invalid dimension for breadth of the layout");
        }
    }

    @Override
    public void createLayout() {
        gameLayout = new Cell[length][breadth];

        int positionX = 0;
        int positionY = 0;
        while(positionX < length) {
            positionY = 0;
            while (positionY < breadth) {
                gameLayout[positionX][positionY] = new ValorCell(((positionX * breadth) + positionY + 1), positionX, positionY);
                positionY ++;
            }
            positionX ++;
        }
    }

    @Override
    public void initLayout() {
        this.createLayout();
        this.setValorInaccessible();
        this.setHeroNexus();
        this.setMonsterNexus();
        this.setCaveSpaces();
        this.setBushSpaces();
        this.setKoulouSpaces();
    }

    public Cell[][] getGameLayout() {
        return gameLayout;
    }

    @Override
    public Cell getCell(Integer positionX, Integer positionY) {
        return this.getGameLayout()[positionX][positionY];
    }

    public CellSpace getCellType(Integer positionX, Integer positionY) {
        return ((ValorCell)this.getCell(positionX, positionY)).getCellType();
    }

    public void setValorInaccessible() {
        for (int column = 2; column < this.breadth; column = column + 3) {
            for(int row = 0; row < this.length; row ++) {
                ((ValorCell)this.getCell(row, column)).setCellType(CellSpace.INACCESSIBLE);
            }
        }
    }

    public void setHeroNexus() {
        for (int column = 0; column < this.breadth; column ++) {
            if (this.getCellType(this.length - 1, column) != CellSpace.INACCESSIBLE) {
                ((ValorCell)this.getCell(this.length - 1, column)).setCellType(CellSpace.HERONEXUS);
            }
        }
    }

    public void setMonsterNexus() {
        for(int column = 0; column < this.breadth; column ++) {
            if (this.getCellType(0, column) != CellSpace.INACCESSIBLE) {
                ((ValorCell)this.getCell(0, column)).setCellType(CellSpace.MONSTERNEXUS);
            }
        }
    }

    public void setSpaces(CellSpace cellType, Integer percentage) {

        int cellIndex = 0, positionX = -1, positionY = -1;
        int lanePos = 0;
        int numCells = ((6 * 2) * percentage ) / 100;
        int temp = numCells;

        for(int lane = 0; lane < 3; lane ++) {
            temp = numCells;
            while (temp > 0) {
                lanePos = Utils.randomNumber.nextInt(12);
                positionY = (lanePos % 2) + (lane * 3);
                lanePos = Utils.randomNumber.nextInt(6);
                positionX = lanePos + 1;

                if (this.getCellType(positionX, positionY) == CellSpace.PLAIN) {
                    ((ValorCell) this.getCell(positionX, positionY)).setCellType(cellType);
                    temp--;
                }
            }
        }
    }

    public void setCaveSpaces() {
        this.setSpaces(CellSpace.CAVE, this.getCaveCellPercent());
    }

    public void setBushSpaces() {
        this.setSpaces(CellSpace.BUSH, this.getBushCellPercent());
    }

    public void setKoulouSpaces() {
        this.setSpaces(CellSpace.KOULOU, this.getKoulouCellPercent());
    }

    @Override
    public void displayLayout() {
        for (int indexI = 0; indexI < length; indexI ++) {
            for (int indexJ = 0; indexJ < breadth; indexJ ++) {
                System.out.print("+-----------------");
            }
            System.out.println("+");
            for (int indexJ = 0; indexJ < breadth; indexJ ++) {
                System.out.print("|" + CellSpace.spaceColor.get(this.getCellType(indexI, indexJ)) + "                 " + BackgroundColors.RESET);
            }
            System.out.println("|");
            for (int indexJ = 0; indexJ < breadth; indexJ++) {
                System.out.print("|" + CellSpace.spaceColor.get(this.getCellType(indexI, indexJ)) + "                 " + BackgroundColors.RESET);
            }
            System.out.println("|");
            for (int indexJ = 0; indexJ < breadth; indexJ ++) {
                System.out.print("|" + CellSpace.spaceColor.get(this.getCellType(indexI, indexJ)) + "                 " + BackgroundColors.RESET);
            }
            System.out.println("|");
        }
        for (int indexJ = 0; indexJ < breadth; indexJ++) {
            System.out.print("+-----------------");
        }
        System.out.println("+");

        System.out.println("\n" + CellSpace.spaceColor.get(CellSpace.INACCESSIBLE) + "   " + BackgroundColors.RESET + " - Inaccessible Spaces");
        System.out.println("" + CellSpace.spaceColor.get(CellSpace.HERONEXUS) + "   " + BackgroundColors.RESET + " - Hero's Nexus");
        System.out.println("" + CellSpace.spaceColor.get(CellSpace.MONSTERNEXUS) + "   " + BackgroundColors.RESET + " - Monster's Nexus");
        System.out.println("" + CellSpace.spaceColor.get(CellSpace.PLAIN) + "   " + BackgroundColors.RESET + " - Plain Spaces");
        System.out.println("" + CellSpace.spaceColor.get(CellSpace.CAVE) + "   " + BackgroundColors.RESET + " - Cave Spaces");
        System.out.println("" + CellSpace.spaceColor.get(CellSpace.BUSH) + "   " + BackgroundColors.RESET + " - Bush Spaces");
        System.out.println("" + CellSpace.spaceColor.get(CellSpace.KOULOU) + "   " + BackgroundColors.RESET + " - Koulou Spaces");
        System.out.println("" + TextColors.PURPLE + " H " + TextColors.RESET + " - Hero Team");
    }


    public Integer getCaveCellPercent() {
        return caveCellPercent;
    }

    public void setCaveCellPercent(Integer caveCellPercent) throws IllegalStateException {
        if (caveCellPercent > 26) {
            throw new IllegalStateException("The percent of Cave spaces in the map cannot be more than 20%");
        }

        this.caveCellPercent = caveCellPercent;
    }

    public Integer getBushCellPercent() {
        return bushCellPercent;
    }

    public void setBushCellPercent(Integer bushCellPercent) throws IllegalStateException {
        if (bushCellPercent > 30) {
            throw new IllegalStateException("The percent of bush Spaces in the map cannot be more than 20%");
        }

        this.bushCellPercent = bushCellPercent;
    }

    public Integer getKoulouCellPercent() {
        return koulouCellPercent;
    }

    public void setKoulouCellPercent(Integer koulouCellPercent) throws IllegalStateException {
        if (koulouCellPercent > 30) {
            throw new IllegalStateException("The percent of koulou spaces in the map cannot be more than 20%");
        }

        this.koulouCellPercent = koulouCellPercent;
    }
}
