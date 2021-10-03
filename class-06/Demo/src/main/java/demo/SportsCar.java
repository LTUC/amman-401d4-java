package demo;

public class SportsCar extends TransportMachine implements Vehicle {

    public SportsCar(int doors) {
        super(doors);
    }

    public void drift() {
        System.out.println("drifting");
    }

    @Override
    public void honkHorn() {
        System.out.println("Honking");
    }
}
