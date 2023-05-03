package com.example.diningphilosophers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Fork {

    private static Logger LOGGER = LoggerFactory.getLogger(Fork.class);

    private final int number;
    private Map<Fork, Philosopher> mapForkPhilosopher = new HashMap<>();

    public Fork(int number) {
        this.number = number;
    }

    public synchronized void takeFork(Philosopher philosopher) {
        if (!(mapForkPhilosopher.isEmpty())) {
            while (!mapForkPhilosopher.containsKey(this)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    LOGGER.warn("The thread was interrupted");
                }
            }
        }
        mapForkPhilosopher.put(this, philosopher);
    }

    public synchronized void putFork(Philosopher philosopher) {
        if (mapForkPhilosopher.containsKey(this) && mapForkPhilosopher.get(this).equals(philosopher)) {
            mapForkPhilosopher.remove(this, philosopher);
            notify();
        }
    }

    public int getNumber() {
        return number;
    }

}
