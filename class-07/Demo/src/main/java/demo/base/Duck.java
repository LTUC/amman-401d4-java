package demo.base;

public abstract class Duck {

    private final String color;
    private final String species;
    private String name;

    public Duck(String color, String species, String name) {
        this.color = color;
        this.species = species;
        this.name = name;
    }

    public abstract void makeNoise();

    public String getColor() {
        return color;
    }

    public String getSpecies() {
        return species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
