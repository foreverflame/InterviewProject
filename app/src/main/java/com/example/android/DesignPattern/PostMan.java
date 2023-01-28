package com.example.android.DesignPattern;

import java.util.ArrayList;


public class PostMan implements Observable {

    ArrayList<Observer> arrayList = new ArrayList<>();

    @Override
    public void add(Observer observer) {
        arrayList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        arrayList.remove(observer);

    }

    @Override
    public void notify(String message) {
        for (Observer observer : arrayList) {
            observer.update(message);
        }
    }
}
