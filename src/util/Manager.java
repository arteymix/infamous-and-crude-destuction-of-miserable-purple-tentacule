/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.HashSet;

/**
 *
 * @author guillaume
 */
public abstract class Manager<T> extends Thread {

    protected HashSet<T> mManaged = new HashSet<T>();

    public abstract void register(T mItem);

    public abstract void unRegister(T mItem);
}
