package com.example.diningphilosophers;

public class Demo {

    public static void main(String[] args) {

        Philosopher[] philosophers = Feast.createPhilosophers(5);

        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }

    }
}
