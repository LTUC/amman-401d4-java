package demo;

public class PaperPlane extends TransportMachine implements Flight {
    public PaperPlane(int doors) {
        super(doors);
    }

    public void crumple() {
        System.out.println("Crumple me");
    }

    @Override
    public void fly() {
        System.out.println("flying with paper");
    }
}
