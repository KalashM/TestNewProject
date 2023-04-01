package com.example.diningphilosophers;

public class Fork {
    private final int number;
    private boolean isFree = true;
    private int philosopherUsingThisFork;

    public Fork(int number) {
        this.number = number;
    }

    public synchronized void takeFork(int philosopherId) {
        while (!isFree) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        philosopherUsingThisFork = philosopherId;
        isFree = false;
    }

    public synchronized void putFork(int philosopherID) {
        if (!isFree && philosopherUsingThisFork == philosopherID) {
            isFree = true;
            notify();
        }
    }

    public int getNumber() {
        return number;
    }

}
