package demo;

public class Human {
    private static final String PROFESSION = "student";
    public static final String NATIONALITY = "arab";

    private int age;
    private double height;
    private double weight;

    private final String name;
    private final String chromosome;

    private Human friend;

    public Human(int age, double height, double weight, String name, String chromosome) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.chromosome = chromosome;
    }

    public String getChromosome() {
        return chromosome;
    }

    public String getName() {
        // lines of code
        // lines of code
        // lines of code
        // lines of code
        // lines of code
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void takeExam(String exam) {
        System.out.println("I took " + exam + " exam");
    }

    public void party() {
        System.out.println("YOLO and VIBING");
    }

    public Human getFriend() {
        return friend;
    }

    public void setFriend(Human friend) {
        this.friend = friend;
    }

    @Override
    public String toString() {
        return "Human{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
