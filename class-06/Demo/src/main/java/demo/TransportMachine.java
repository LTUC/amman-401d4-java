package demo;

import java.util.Date;

public class TransportMachine {
    private int doors;
    private int horsePower;
    private double topSpeed;
    private double acceleration;

    private String structure;
    private String model;
    private String color;

    private Date manYear;

    public TransportMachine(int doors) {
        this.doors = doors;
    }

    public boolean startEngine() {
        System.out.println("Juggaaa Jugggga: engine started");
        return true;
    }

    public void applyBreak() {
        System.out.println("BREAKING SCCCCCRRRZZZ");
    }

    public String steer(String direction) {
        return "";
    }

    public void accelerate(int accelerate) {
        System.out.println("VRROOMMMMMMM VROOOOOMMM");
    }
}
