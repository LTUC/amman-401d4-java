import demo.*;
import demo.base.Duck;
import demo.type.DuckType;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Duck Simulator");

        Duck donald = new Alabio("White", "Mallard", "Donald");
        Duck daffy = new CanvasBack("Brown White", "CanvasBack", "Daffy");
        Duck rubberDuck = new RubberDuck("Yellow", "Rubber", "Rubber Ducky");
        NormalDuck normalDuck = new NormalDuck("Red", "Normal", "Normally");

        List<Duck> ducks = new ArrayList<>();
        ducks.add(donald);
        ducks.add(daffy);
        ducks.add(rubberDuck);
        ducks.add(normalDuck);

        simulator(ducks);
    }

    private static void simulator(List<Duck> ducks) {
        for (Duck duck : ducks) {
            System.out.println("My name is => " + duck.getName());
            duck.makeNoise();

            if (duck instanceof Alabio) {
                Alabio alabioDuck = (Alabio) duck;
                alabioDuck.drive();
            }

            if (duck instanceof DuckType) {
                DuckType duckType = (DuckType) duck;
                duckType.eat();
                duckType.fly();
            }

            System.out.println("\n");
        }
    }
}