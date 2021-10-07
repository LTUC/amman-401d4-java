package generic;

public class Petra {

    private String name;

    public Petra(String name) {
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
        return "Petra{" +
                "name='" + name + '\'' +
                '}';
    }
}
