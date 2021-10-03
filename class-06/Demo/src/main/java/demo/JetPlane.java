package demo;

public class JetPlane extends TransportMachine implements Flight {
    public JetPlane(int doors) {
        super(doors);
    }

    public void shootMissle() {
        System.out.println("shoot missles");
    }

    @Override
    public void fly() {
        System.out.println("Normal Flying");
    }
}
