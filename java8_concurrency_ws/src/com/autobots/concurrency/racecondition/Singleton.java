package com.autobots.concurrency.racecondition;

public class Singleton {

    private static Singleton instance;

    private Singleton(){
    }

    /*
        Singleton Pattern is the classic example of Race Condition.

        If more than two requests come in simultaneously for the first time to get the Singleton object, it might result
         in object being created twice overriding the first one. This race condition can be prevented by synchronzing
          getInstance() method.
     */
    public static synchronized Singleton getInstance(){
        if(getInstance()==null){
            instance = new Singleton();
        }
        return instance;
    }
}
