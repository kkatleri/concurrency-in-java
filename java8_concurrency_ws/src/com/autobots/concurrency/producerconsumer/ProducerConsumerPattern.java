package com.autobots.concurrency.producerconsumer;

public class ProducerConsumerPattern {

    /*
        Producer-Consumer pattern allows multiple threads to talk to each other using shared resource.

        In below example, producer and consumer threads are working on shared resources `buffer` & `count`. Producer
        thread is adding values to the buffer whereas consumer thread accessing/consuming values from buffer.

        This will introduce classic Race condition and deadlock situations.

        To avoid above conditions, following steps need to be taken -
        1. Synchronize producer and consume methods on common lock object. This is not sufficient though as this will
         only solve Race condition but not the deadlock.
        2. To solve deadlock, we will need to implement wait/notify pattern for threads to communicate to each other
        about the state of the shared resources.
     */
    static int[] buffer;
    static int count;

    private static Object lock = new Object();


    public static void main(String[] args) throws InterruptedException {

        buffer = new int[10];
        count = 0;

        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Runnable produceTask = () -> {
            for(int i=0; i<50; i++)
                producer.produce();
        };
        Runnable consumeTask = () -> {
            for(int i=0; i<50; i++)
                consumer.consume();
        };

        Thread producerThread = new Thread(produceTask);
        Thread consumerThread = new Thread(consumeTask);

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        System.out.println("Buffer size - " + count);
    }

    static class Producer{
        public void produce(){
            synchronized (lock) {
                if (isFull(buffer)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                buffer[count++] = 1;
                lock.notifyAll();
            }

        }
    }
    static class Consumer{
        public static void consume(){
            synchronized (lock) {
                if (isEmpty(buffer)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                buffer[--count] = 0;
                lock.notifyAll();
            }
        }
    }
    private static boolean isEmpty(int[] buffer) {
        return count==0;
    }
    private static boolean isFull(int[] buffer) {
        return count==buffer.length;
    }
}
