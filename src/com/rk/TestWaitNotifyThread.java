package com.rk;

public class TestWaitNotifyThread {
    private ThreadA ta;
    private ThreadB tb;
    private String msg;

    public TestWaitNotifyThread() {
        ta = new ThreadA();
        tb = new ThreadB();
    }

    class ThreadA implements Runnable {

        Thread th = new Thread(this);

        public ThreadA() {
            th.start();
        }

        public void run() {
            int i = 0;
            while (i < 100) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e);
                }
                setMessage("Hi" + i);
                i++;
            }
        }
    }

    class ThreadB implements Runnable {
        Thread th = new Thread(this);

        public ThreadB() {
            th.start();
        }

        public void run() {
            int i = 0;
            while (true) {
                waitForMessage();
                System.out.println(msg);
                i++;
            }
        }
    }

    public synchronized void setMessage(String message) {
        msg = message;
        System.out.println("set message : " + msg);
        notify();
    }

    public synchronized void waitForMessage() {
        try {
            wait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
    }

    public static void main(String arg[]) {
        new TestWaitNotifyThread();
    }
}
