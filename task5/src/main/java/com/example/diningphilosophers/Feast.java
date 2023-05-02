package com.example.diningphilosophers;

public class Feast {
    private static int numberOfPhilosophers = 0;

    public static int getNumberOfPhilosophers() {
        return numberOfPhilosophers;
    }

    public static Philosopher[] createPhilosophers(int n) {
        numberOfPhilosophers = n;
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
}
