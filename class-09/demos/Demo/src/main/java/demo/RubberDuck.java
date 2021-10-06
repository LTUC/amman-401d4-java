package demo;

import demo.base.Duck;

public class RubberDuck extends Duck {
    public RubberDuck(String color, String species, String name) {
        super(color, species, name);
    }

    @Override
    public void makeNoise() {
        System.out.println("Squeak Squeaks");
    }
}
