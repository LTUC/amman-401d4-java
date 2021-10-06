package demo;

import demo.base.Duck;
import demo.behaviours.Drive;

public class NormalDuck extends Duck implements Drive {
    public NormalDuck(String color, String species, String name) {
        super(color, species, name);
    }

    @Override
    public void makeNoise() {
        System.out.println("kjsdfkjsdjfhsdjk");
    }

    @Override
    public void drive() {

    }
}
