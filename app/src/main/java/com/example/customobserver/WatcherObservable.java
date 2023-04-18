package com.example.customobserver;

import java.util.ArrayList;

/**
 * @description:
 * @author: huangyonghuang
 * @date: 2023/4/18
 */
public class WatcherObservable implements Observable {

    private ArrayList<Observer> list = new ArrayList<>();
    private String message;

    @Override
    public void add(Observer observer) {
        list.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        if (list.contains(observer)) {
            list.remove(observer);
        }
    }

    @Override
    public void notifyObserve() {
        for (Observer observer : list) {
            observer.update(message);
        }
    }

    @Override
    public void pushMessage(String message) {
        this.message = message;
        notifyObserve();
    }
}
