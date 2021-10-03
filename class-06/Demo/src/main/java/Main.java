import com.sun.jdi.connect.Connector;
import demo.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello class welcome");

        // cant do this
//        Truck dumpTruck = new SportsCar(800, "Slim Profile", "Spider", "Red");

        TransportMachine sportCar = new SportsCar(2);
        TransportMachine miniVan = new MiniVan(5);
        TransportMachine motorBike = new MotorBike(0);
        TransportMachine truck = new Truck(4);

        SpaceShuttle shuttle = new SpaceShuttle(2);
        shuttle.fly();
        shuttle.startEngine();
        shuttle.accelerate(67);

        SportsCar sportsCar = new SportsCar(6);
        sportsCar.honkHorn();
        sportsCar.startEngine();

        JetPlane plane = new JetPlane(4);
        plane.fly();

        Flight sputnikApollo = new SpaceShuttle(6);
        sputnikApollo.fly();

        Flight paperPlane = new PaperPlane(6);
        sputnikApollo.fly();

        TransportMachine f16 = new JetPlane(6);
        f16.accelerate(89);

        gamePlay(miniVan);
        gamePlay(sportCar);

        flightSimulator(sputnikApollo);
        flightSimulator(paperPlane);
    }

    private static void gamePlay(TransportMachine transportMachine) {
        if (transportMachine instanceof MiniVan) {
            // casting
            MiniVan miniVan = (MiniVan) transportMachine;
            miniVan.deliver();
        }

        if (transportMachine instanceof SportsCar) {
            // casting
            SportsCar sportsCar = (SportsCar) transportMachine;
            sportsCar.drift();
        }
    }

    private static void flightSimulator(Flight areoDynamicThings) {
        if (areoDynamicThings instanceof PaperPlane paperPlane) {
            paperPlane.crumple();
        } else {
            SpaceShuttle spaceShuttle = (SpaceShuttle) areoDynamicThings;
            spaceShuttle.goToMoon();
        }
    }
}
