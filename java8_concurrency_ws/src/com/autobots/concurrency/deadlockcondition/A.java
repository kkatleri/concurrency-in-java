package com.autobots.concurrency.deadlockcondition;

public class A {

    private Object key1 = new Object();
    private Object key2 = new Object();

    public void a(){
        synchronized (key1){
            System.out.println("["+Thread.currentThread().getName() + "] I am in method a()...");
            b();
        }
    }

    public void b(){
        synchronized (key2){
            System.out.println("["+Thread.currentThread().getName() + "] I am in method b()...");
            c();
        }
    }

    public void c(){
        synchronized (key1){
            System.out.println("["+Thread.currentThread().getName() + "] I am in method c()...");
        }
    }
}
