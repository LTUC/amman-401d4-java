package demo;

public class MiniVan extends TransportMachine {

    private boolean hasSlidingDoors;

    public MiniVan(int doors) {
        // calls the parent class constructor
        super(doors);
    }

    public void deliver() {
        System.out.println("Deliver");
    }
}
