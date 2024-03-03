package com.autobots.concurrency.racecondition;

public class LongWrapper {

    private long l;

    public LongWrapper(long l){ this.l = l;}
    public long getValue(){
        return l;
    }

    public synchronized void incrementValue(){
        //System.out.println(Thread.currentThread().getName() + " is incrementing..");
        l = l+1;
    }
}
