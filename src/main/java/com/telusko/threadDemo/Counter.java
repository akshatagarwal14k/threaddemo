package com.telusko.threadDemo;

public class Counter implements Runnable{
    int count;
    public synchronized void increment(){
        count++;
    }

    @Override
    public void run() {
        for(int i=1;i<=10000;i++){
            increment();
        }
    }
}
