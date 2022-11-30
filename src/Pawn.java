/**
 * Pawn Class: This class represents the basic pawn and the characteristics which is required for it.
 */

public abstract class Pawn {
    private String type;
    private String color;
    private String name;
    private String symbol;

    protected Pawn() { }

    protected Pawn(String name) {
        this.setName(name);
        this.setSymbol("NA");
    }

    protected Pawn(String name, String color, String type) {
        this.setName(name);
        this.setColor(color);
        this.setType(type);
        this.setSymbol("NA");
    }

    protected void init(String name) {
        this.setName(name);
    }

    protected void setType(String type) {
        this.type = type;
    }

    protected String getType() {
        return this.type;
    }

    protected void setColor(String color) {
        this.color = color;
    }

    protected String getColor() {
        return this.color;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getName() {
        return this.name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
