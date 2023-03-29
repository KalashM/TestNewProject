package com.example.diningphilosophers;

public class Philosopher implements Runnable {
    private int number;

    public Philosopher(int n) {
        this.number = n;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running for philosopher " + number);
    }
}
