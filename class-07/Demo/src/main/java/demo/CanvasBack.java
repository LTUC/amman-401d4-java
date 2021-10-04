package demo;

import demo.base.Duck;
import demo.type.DuckType;

public class CanvasBack extends Duck implements DuckType {
    private boolean aggression;
    private int strength;

    public CanvasBack(String color, String species, String name) {
        super(color, species, name);
    }

    @Override
    public void makeNoise() {
        System.out.println("Quicky Quack Quang");
    }

    public void setAggression(boolean aggression) {
        this.aggression = aggression;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public boolean isAggression() {
        return aggression;
    }

    // behavious implemented from the iinterface
    @Override
    public void eat() {
        System.out.println("I eat fish");
    }

    @Override
    public void fly() {
        System.out.println("I fly in the Alps");
    }
}
