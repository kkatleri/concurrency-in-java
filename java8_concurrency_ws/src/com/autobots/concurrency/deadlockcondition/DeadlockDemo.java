package com.autobots.concurrency.deadlockcondition;

public class DeadlockDemo {

    /*
        Deadlock condition occurs quiet often in Multi-threaded environment. It occurs when two or more threads are
        waiting on each other to release lock of shared resource.

        In the below example, Thread-0 has the key1 to call method a() and the waiting on key2 to call method b(),
        whereas key2 is with Thread-1 to call method b() and is waiting on key1 to call method c()
     */
    public static void main(String[] args) throws InterruptedException {

        A a = new A();
        Runnable runnable1 = ()-> a.a();
        Runnable runnable2 = ()-> a.b();

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Main thread ended..");
    }
}
