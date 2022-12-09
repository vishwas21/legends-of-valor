/**
 * Layout Interface: The interface helps all the games create a layout and set a few rules which must be followed.
 */

public interface Layout {

    public Integer getLength();

    public void setLength(Integer length);

    public Integer getBreadth();

    public void setBreadth(Integer breadth);

    public void createLayout();

    public void displayLayout();

    public void initLayout();

    public Cell[][] getGameLayout();

    public Cell getCell(Integer positionX, Integer positionY);

}
