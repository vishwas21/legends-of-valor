/**
 * Pawn Class: This class represents the basic pawn and the characteristics which is required for it.
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 20, 2022
 */

public abstract class Pawn {
    private String type;
    private String color;
    private String name;

    protected Pawn() { }

    protected Pawn(String name) {
        this.setName(name);
    }

    protected Pawn(String name, String color, String type) {
        this.setName(name);
        this.setColor(color);
        this.setType(type);
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
}
