package com.example.diningphilosophers;

public class Fork {
    private int number;
    private boolean isFree = true;

    public Fork(int number) {
        this.number = number;
    }

    public void getFork() {
        this.isFree = false;
    }

    public void putFork() {
        this.isFree = true;
    }

    public int getNumber() {
        return number;
    }

}
