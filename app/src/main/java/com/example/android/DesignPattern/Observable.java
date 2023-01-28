package com.example.android.DesignPattern;


public interface Observable {

    void add(Observer observer);

    void remove(Observer observer);

    void notify(String message);
}
