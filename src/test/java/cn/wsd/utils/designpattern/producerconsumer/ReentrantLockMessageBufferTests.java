package cn.wsd.utils.designpattern.producerconsumer;

public class ReentrantLockMessageBufferTests {

	public static void main(String[] args) {
		ReentrantLockMessageBuffer<Integer> buffer = new ReentrantLockMessageBuffer<>(5);
		Thread producer1 = new Thread(new Producer(buffer));
		Thread producer2 = new Thread(new Producer(buffer));
		Thread producer3 = new Thread(new Producer(buffer));
		Thread producer4 = new Thread(new Producer(buffer));
		Thread producer5 = new Thread(new Producer(buffer));
		Thread consumer1 = new Thread(new Consumer(buffer));
		Thread consumer2 = new Thread(new Consumer(buffer));
		producer1.start();
		producer2.start();
		producer3.start();
		producer4.start();
		producer5.start();
		consumer1.start();
		consumer2.start();
	}

}
