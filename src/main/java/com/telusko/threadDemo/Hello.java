package com.telusko.threadDemo;

public class Hello implements Runnable{
    public void run() {
        for(int i=0;i<5;i++){
            System.out.println("Hello" + " " + Thread.currentThread().getPriority());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
