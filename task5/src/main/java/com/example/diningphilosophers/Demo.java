package com.example.diningphilosophers;

public class Demo {
       /*Philosopher philosopher1 = new Philosopher(1);
        Philosopher philosopher2 = new Philosopher(2);
        Philosopher philosopher3 = new Philosopher(3);
        Philosopher philosopher4 = new Philosopher(4);
        Philosopher philosopher5 = new Philosopher(5);

        new Thread(philosopher1).start();
        new Thread(philosopher2).start();
        new Thread(philosopher3).start();
        new Thread(philosopher4).start();
        new Thread(philosopher5).start();*/

    public static Philosopher[] createPhilosophers(int n) {

        Fork[] forks = new Fork[n];

        for (int i = 0; i < n; i++) {
            forks[i] = new Fork(i);
        }

        Philosopher[] philosophers = new Philosopher[n];

        for (int i = 0; i < n; i++) {

            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % n];

            philosophers[i] = new Philosopher(i, leftFork, rightFork);

        }

        return philosophers;

    }

    public static void main(String[] args) {

        Philosopher[] philosophers = createPhilosophers(5);

        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }

    }
}
