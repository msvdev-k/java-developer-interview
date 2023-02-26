package org.msvdev.example.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainCounter {

    public static class Counter {

        private final Lock locker = new ReentrantLock();

        private int value;

        public int getValue() {
            return value;
        }

        public void increment() {
            locker.lock();
            try {
                value++;
            }
            finally {
                locker.unlock();
            }
        }

        public void decrement() {
            locker.lock();
            try {
                value--;
            }
            finally {
                locker.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.decrement();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter.getValue());
    }
}
