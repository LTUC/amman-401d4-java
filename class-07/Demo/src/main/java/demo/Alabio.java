package demo;

import demo.base.Duck;
import demo.behaviours.Drive;
import demo.type.Eat;

public class Alabio extends Duck implements Eat, Drive {
    public Alabio(String color, String species, String name) {
        super(color, species, name);
    }

    @Override
    public void makeNoise() {
        System.out.println("Quack Quacker Quackle");
    }

    @Override
    public void drive() {
        System.out.println("I am driving");
    }

    @Override
    public void eat() {
        System.out.println("I eat caviar and sea weed");
    }

    @Override
    public void fly() {
        System.out.println("I fly north");
    }

    @Override
    public void eatWorms() {

    }
}
