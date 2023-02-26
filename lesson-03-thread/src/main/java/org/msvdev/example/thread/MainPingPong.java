package org.msvdev.example.thread;

public class MainPingPong {

    private final Object mon = new Object();

    private static final String PING = "ping";
    private static final String PONG = "pong";

    private static final int COUNT = 4;

    private volatile String currentState = PING;


    public static void main(String[] args) {
        MainPingPong pingPong = new MainPingPong();

        new Thread(pingPong::ping).start();
        new Thread(pingPong::pong).start();
    }


    public void ping() {
        synchronized (mon) {
            try {
                for (int i = 0; i < COUNT; i++) {
                    while (!currentState.equals(PING)) {
                        mon.wait();
                    }
                    System.out.println(PING);
                    currentState = PONG;
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pong() {
        synchronized (mon) {
            try {
                for (int i = 0; i < COUNT; i++) {
                    while (!currentState.equals(PONG)) {
                        mon.wait();
                    }
                    System.out.println(PONG);
                    currentState = PING;
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
