package com.example.diningphilosophers;

public class Demo {
    public static void main(String[] args) {
        Philosopher philosopher1 = new Philosopher(1);
        Philosopher philosopher2 = new Philosopher(2);
        Philosopher philosopher3 = new Philosopher(3);
        Philosopher philosopher4 = new Philosopher(4);
        Philosopher philosopher5 = new Philosopher(5);

        new Thread(philosopher1).start();
        new Thread(philosopher2).start();
        new Thread(philosopher3).start();
        new Thread(philosopher4).start();
        new Thread(philosopher5).start();
    }
}
