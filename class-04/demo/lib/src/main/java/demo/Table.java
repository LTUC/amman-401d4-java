/*
written by Mr Jones
 */
package demo;

/**
 * This is a model of a real world table
 */
public class Table {
    // constants
    public static final String TABLE_COLOR = "brown";

    // number of legs
    private int legs;

    private boolean hasDrawers;

    // used to be length width and height
    private Dimensions dimensions;

    private String materialType; // default access modifier
    private String color; // protected access nmmodifier

    public Table() {
        legs  = 4;
    }

    public Table(boolean hasDrawers, String materialType, String color, Dimensions tableDimensions) {
        this.hasDrawers = hasDrawers;
        this.materialType = materialType;
        this.color = color;
        legs = 4;
        dimensions = tableDimensions;
    }

    public Table(int legs, boolean hasDrawers, String materialType, String color) {
        this.legs = legs;
        this.hasDrawers = hasDrawers;
        this.materialType = materialType;
        this.color = color;
        legs = 4;
    }

    // setters and getters

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public boolean isHasDrawers() {
        return hasDrawers;
    }

    public void setHasDrawers(boolean hasDrawers) {
        this.hasDrawers = hasDrawers;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void eatOnTable(String food) {
        System.out.println("i am eating " + food);
    }

    public double pi() {
        return Math.PI;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    @Override
    public String toString() {
        return "Table{" +
                "legs=" + legs +
                ", hasDrawers=" + hasDrawers +
                ", materialType='" + materialType + '\'' +
                ", color='" + color + '\'' +
                ", dimensions='" + dimensions + '\'' +
                '}';
    }
}
