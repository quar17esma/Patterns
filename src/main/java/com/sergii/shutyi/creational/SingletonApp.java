package com.sergii.shutyi.creational;

public class SingletonApp {
    public static void main(String[] args) throws InterruptedException {
        final int SIZE = 1000;
        Thread thread[] = new Thread[SIZE];
        for (int i = 0; i < SIZE; i++) {
            thread[i] = new Thread(new R());
            thread[i].start();
        }
        for (int i = 0; i < SIZE; i++) {
            thread[i].join();
        }
        System.out.println(Singleton.counter);
    }
}

class R implements Runnable{
    @Override
    public void run() {
        Singleton.getInstance();
    }
}

class Singleton{
    public static int counter = 0;
    private static volatile Singleton instance = null;

    private Singleton(){
        counter++;
    }

    public static Singleton getInstance(){
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
