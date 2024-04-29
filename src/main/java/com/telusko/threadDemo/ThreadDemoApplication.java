package com.telusko.threadDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThreadDemoApplication {

	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(ThreadDemoApplication.class, args);

//		Runnable obj1 = new Hi();
//		Runnable obj2 = new Hello();
//
//		Thread t1 = new Thread(obj1,"Hi Thread");
//		Thread t2 = new Thread(obj2,"Hello Thread");
//
//		System.out.println(t1.getName());
//		System.out.println(t2.getName());
//
//		t1.setPriority(Thread.MIN_PRIORITY);
//		t2.setPriority(Thread.MAX_PRIORITY);

//		System.out.println(t1.getPriority());
//		System.out.println(t2.getPriority());

//		t1.start();
//		Thread.sleep(10);
//		t2.start();

		//Will wait for t1 and t2 to join the main thread back
//		t1.join();
//		t2.join();

		//To check if a thread is alive
//		System.out.println(t1.isAlive());

		//		System.out.println("Bye");

//		Counter c = new Counter();
//		Thread t3 = new Thread(c);
//		Thread t4 = new Thread(c);
//
//		t3.start();
//		t4.start();
//
//		t3.join();
//		t4.join();
//
//		System.out.println(c.count);


		Q q = new Q();
		new Producer(q);
		new Consumer(q);


	}

}

class Q{
	private int num;
	boolean valueSet = false;

	public synchronized void put(int num) throws InterruptedException {
		while(valueSet){
			wait();
		}
		System.out.println("Put: " + num);
		this.num = num;
		valueSet = true;
		notify();
	}

	public synchronized void get() throws InterruptedException {
		while(!valueSet){
			wait();
		}
		System.out.println("Get: " + num);
		valueSet = false;
		notify();
	}
}

class Producer implements Runnable{

	Q q;

	public Producer(Q q){
		this.q = q;
		Thread t = new Thread(this,"Producer");
		t.start();
	}

	@Override
	public void run() {
		int i = 0;
		while (true){
			try {
				q.put(i++);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}


class Consumer implements Runnable{

	Q q;

	public Consumer(Q q){
		this.q = q;
		Thread t = new Thread(this,"Consumer");
		t.start();
	}

	@Override
	public void run() {
		int i = 0;
		while (true){
			try {
				q.get();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}


