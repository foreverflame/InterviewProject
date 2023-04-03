package com.example.aidl.server;

import android.os.IInterface;
import android.os.RemoteException;

import com.example.aidl.Book;

/**
 * @description:
 * @author: huangyonghuang
 * @date: 2023/3/29
 */
public interface BookManager extends IInterface {
    void addBook(Book book) throws RemoteException;
}
