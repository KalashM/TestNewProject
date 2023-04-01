package com.example.diningphilosophers;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {

    private static final Semaphore SEMAPHORE = new Semaphore(4);

    private int id;
    private Fork leftFork;
    private Fork rightFork;

    public Philosopher(int n, Fork leftFork, Fork rightFork) {
        this.id = n;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running for philosopher " + id);
        while (true) {
            System.out.println("Philosopher " + id + " is thinking...");
            SEMAPHORE.acquireUninterruptibly();
            rightFork.takeFork(id);
            System.out.println("Philosopher " + id + " took the right fork " + rightFork.getNumber());
            leftFork.takeFork(id);
            SEMAPHORE.release();
            System.out.println("Philosopher " + id + " took the left fork " + leftFork.getNumber());
            System.out.println("Philosopher " + id + " is eating...");
            leftFork.putFork(id);
            System.out.println("Philosopher " + id + " has put down the left fork " + leftFork.getNumber());
            rightFork.putFork(id);
            System.out.println("Philosopher " + id + " has put down the right fork " + rightFork.getNumber());
        }
    }
}
