package MultiThreadingHW;

import java.io.*;

public class Main {

    private static class MFU {
        Object mon1 = new Object();
        Object mon2 = new Object();

        public void print(int pageCount) throws InterruptedException {
            synchronized (mon1){
                for (int i = 0; i < pageCount; i++){
                    System.out.println("Напечатана " + (i + 1) + "страница");
                    Thread.sleep(50);
                }

            }
        }

        public void scan(int pageCount) throws InterruptedException {
            synchronized (mon2) {
                for (int i = 0; i < pageCount; i++){
                    System.out.println("Отсканирована " + (i + 1) + "страница");
                    Thread.sleep(50);
                }
            }
        }
    }

    private static char currentLetter = 'A';
    static Object monitor = new Object();
    public static void main(String[] args) {

        //task1();

        //task2();
        MFU mfu = new MFU();
        new Thread(() -> {
            try {
                mfu.print(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                mfu.print(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                mfu.scan(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                mfu.scan(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void task2() {
        Thread t1 = new Thread(new MyThread("12345649887"));
        Thread t2 = new Thread(new MyThread("afdsafsdfasf"));
        Thread t3 = new Thread(new MyThread("opopopopopopo"));
        t1.start();
        try {
            t1.join();
            t2.start();
            t2.join();
            t3.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void task1() {
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor){
                        while (currentLetter != 'A'){
                            monitor.wait();
                        }
                        System.out.print('A');
                        currentLetter = 'B';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor){
                        while (currentLetter != 'B'){
                            monitor.wait();
                        }
                        System.out.print('B');
                        currentLetter = 'C';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor){
                        while (currentLetter != 'C'){
                            monitor.wait();
                        }
                        System.out.print('C');
                        currentLetter = 'A';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
