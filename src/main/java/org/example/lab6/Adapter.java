package org.example.lab6;

public class Adapter extends Original implements ITarget {
    @Override
    public void ClientDouble(double value) {
        this.OriginalDouble(value);
    }

    @Override
    public void ClientInt(int value) {
        this.OriginalInt(value * 2);
    }

    @Override
    public void ClientChar(char value) {
        for (int i = 0; i < 5; i++) {
            this.OriginalChar(value);
        }
    }
}
