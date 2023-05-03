package com.example.diningphilosophers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {

    private static final Semaphore SEMAPHORE = new Semaphore(Feast.getNumberOfPhilosophers() - 1);
    private static Logger LOGGER = LoggerFactory.getLogger(Philosopher.class);

    private int philosopherId;
    private Fork leftFork;
    private Fork rightFork;

    public Philosopher(int n, Fork leftFork, Fork rightFork) {
        this.philosopherId = n;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public int getPhilosopherId() {
        return philosopherId;
    }

    void performAction(String action) {
       int waitTime = ThreadLocalRandom.current().nextInt(0,1000);
       LOGGER.info("Philosopher " + philosopherId + " " + action + " for " + waitTime + " ms");
    }

    @Override
    public void run() {
        LOGGER.info("Log from {}", Philosopher.class.getSimpleName());
        LOGGER.info(Thread.currentThread().getName() + " is running for philosopher " + philosopherId);
        while (!isInterrupted()) {
            SEMAPHORE.acquireUninterruptibly();
            rightFork.takeFork(this);
            LOGGER.info("Philosopher " + philosopherId + " took the right fork " + rightFork.getNumber());
            leftFork.takeFork(this);
            LOGGER.info("Philosopher " + philosopherId + " took the left fork " + leftFork.getNumber());
            SEMAPHORE.release();
            performAction("eats");
            leftFork.putFork(this);
            LOGGER.info("Philosopher " + philosopherId + " has put down the left fork " + leftFork.getNumber());
            rightFork.putFork(this);
            LOGGER.info("Philosopher " + philosopherId + " has put down the right fork " + rightFork.getNumber());
            performAction("thinks");
        }
    }
}
