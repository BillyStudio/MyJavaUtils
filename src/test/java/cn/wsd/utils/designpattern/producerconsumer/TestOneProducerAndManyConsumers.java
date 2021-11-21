package cn.wsd.utils.designpattern.producerconsumer;

import org.junit.Test;

// 单个生产者，多个消费者
public class TestOneProducerAndManyConsumers {

	@Test
	public void testOneProducerManyConsumersUsingReentrantMessageBuffer() {
		ReentrantLockMessageBuffer<Integer> messageBuffer = new ReentrantLockMessageBuffer<>(5);
		Producer singleProducer = new Producer(messageBuffer);
		Consumer consumer1 = new Consumer(messageBuffer);
		Consumer consumer2 = new Consumer(messageBuffer);
		Consumer consumer3 = new Consumer(messageBuffer);
		new Thread(() -> {
			System.out.println("Producer starts");
			singleProducer.run();
			System.out.println("Producer ends");
		}).start();
		new Thread(() -> {
			System.out.println("Consumer 1 starts");
			consumer1.run();
			System.out.println("Consumer 1 ends");
		}).start();
		new Thread(() -> {
			System.out.println("Consumer 2 starts");
			consumer2.run();
			System.out.println("Consumer 2 ends");
		}).start();
	}
}
