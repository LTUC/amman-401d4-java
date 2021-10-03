package demo;

public class SpaceShuttle extends TransportMachine implements Flight {
    public SpaceShuttle(int doors) {
        super(doors);
    }

    public void goToMoon() {
        System.out.println("go to moon");
    }

    @Override
    public void fly() {
        System.out.println("Blast off");
    }
}
