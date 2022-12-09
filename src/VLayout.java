/**
 * VLayout Interface: This Interface is used to show the rules for the Valor game. This will inherit the rules from the Layout Interface.
 */

public interface VLayout extends Layout {

    CellSpace getCellType(Integer positionX, Integer positionY);

    void setValorInaccessible();

    void setHeroNexus();

    void setMonsterNexus();

    void setSpaces(CellSpace cellType, Integer percentage);

    void setCaveSpaces();

    void setBushSpaces();

    void setKoulouSpaces();

}
