package com.autobots.concurrency.racecondition;

public class RaceConditionDemo {

    /*
        This example demonstrates classic Race Condition problem in Multi-threaded programming

        In this example incrementValue() method on LongWrapper object is subject to race condition when run under
        multi-thread environment because its reading and updating the value of long at the same time.

        When 2 threads enter that method at the same, they will read same value of l and increment thus resulting in
        incorrect result.

        In-order to prevent Race condition, we need to synchronize incrementValue() method by adding synchronized
        keyword in the method declaration. Synchornized keyword will prevent mutliple thread entering into the method
         at the 'same' time.
    */

    public static void main(String[] args) throws InterruptedException {

        LongWrapper longWrapper = new LongWrapper(0);

        Runnable runnable = () -> {
            for(int i=0; i<1_000; i++)
                longWrapper.incrementValue();
        };

        Thread[] threads = new Thread[1_000];

        for(int i=0; i<1_000; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
            threads[i].setName("Thread - " + i);
        }

        for(int i=0; i<1_000; i++) {
            threads[i].join();
        }

        System.out.print("Final long value - " + longWrapper.getValue());
    }
}
