/**
 * Item Abstract Class: This class holds the default details of all the items and its functionality. This is an abstract which can be extended by other games as well.
 */

public abstract class Item {

    private String name;
    private Integer price;
    private Integer level;

    public Item(String name, Integer price, Integer level) {
        this.setName(name);
        this.setPrice(price);
        this.setLevel(level);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
