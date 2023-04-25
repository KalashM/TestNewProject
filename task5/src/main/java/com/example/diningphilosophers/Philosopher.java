package com.example.diningphilosophers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {

    private static final Semaphore SEMAPHORE = new Semaphore(4);
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

    @Override
    public void run() {
        LOGGER.info("Log from {}", Philosopher.class.getSimpleName());
        LOGGER.info(Thread.currentThread().getName() + " is running for philosopher " + philosopherId);
        while (true) {
            LOGGER.info("Philosopher " + philosopherId + " is thinking...");
            SEMAPHORE.acquireUninterruptibly();
            rightFork.takeFork(philosopherId);
            LOGGER.info("Philosopher " + philosopherId + " took the right fork " + rightFork.getNumber());
            leftFork.takeFork(philosopherId);
            SEMAPHORE.release();
            LOGGER.info("Philosopher " + philosopherId + " took the left fork " + leftFork.getNumber());
            LOGGER.info("Philosopher " + philosopherId + " is eating...");
            leftFork.putFork(philosopherId);
            LOGGER.info("Philosopher " + philosopherId + " has put down the left fork " + leftFork.getNumber());
            rightFork.putFork(philosopherId);
            LOGGER.info("Philosopher " + philosopherId + " has put down the right fork " + rightFork.getNumber());
        }
    }
}
