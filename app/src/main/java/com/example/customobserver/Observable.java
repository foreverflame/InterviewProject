package com.example.customobserver;

/**
 * @description: 定义一个被观察者，提供增加，移除，发布，推送功能
 * @author: huangyonghuang
 * @date: 2023/4/18
 */
public interface Observable {

    void add(Observer observer);

    void remove(Observer observer);

    void notifyObserve();

    void pushMessage(String message);
}
