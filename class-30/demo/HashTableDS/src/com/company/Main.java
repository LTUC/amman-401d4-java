package com.company;

public class Main {

    public static void main(String[] args) {

        HashTable<String, Integer> studentAllowances = new HashTable<>();
        studentAllowances.add("John", 300);
        studentAllowances.add("Sally", 400);
        studentAllowances.add("Tom", 200);
        studentAllowances.add("Jade", 100);
        studentAllowances.add("Doa", 600);
        studentAllowances.add("Farah", 700);
        studentAllowances.add("Jason", 90000);
        studentAllowances.add("Morgan", 800);
        studentAllowances.add("Naim", 1000);
        studentAllowances.add("Qusai", 1500);
        studentAllowances.add("Deyaa", 10000);

        System.out.println("The size is => " + studentAllowances.getSize());
        System.out.println("The Sally allowance is => " + studentAllowances.get("Sally"));
        System.out.println("Delete Sally => " + studentAllowances.remove("Sally"));
        System.out.println("The size is => " + studentAllowances.getSize());
    }
}
