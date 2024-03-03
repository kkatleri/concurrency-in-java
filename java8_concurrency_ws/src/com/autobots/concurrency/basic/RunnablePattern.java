package com.autobots.concurrency.basic;

public class RunnablePattern {

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            System.out.println("I am running in " + Thread.currentThread().getName());
        };

        Thread t = new Thread(runnable);
        t.start();

        t.join();

        System.out.println("Main thread finished");

    }
}
