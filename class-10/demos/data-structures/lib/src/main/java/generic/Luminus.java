package generic;

public class Luminus {

    private String name;

    public Luminus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Luminus{" +
                "name='" + name + '\'' +
                '}';
    }
}
