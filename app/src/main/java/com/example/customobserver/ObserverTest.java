package com.example.customobserver;

/**
 * @description:
 * @author: huangyonghuang
 * @date: 2023/4/18
 */
public class ObserverTest {
    public static void main(String[] args) {
        WatcherObservable watcherObservable = new WatcherObservable();
        UserObserver ryan = new UserObserver("ryan");
        UserObserver mike = new UserObserver("mike");
        watcherObservable.add(ryan);
        watcherObservable.add(mike);
        watcherObservable.pushMessage("第三次分配来了！实现共同富裕再放大招！");
    }
}
