package cn.wsd.utils.designpattern.producerconsumer;

// 单个生产者，单个消费者
import java.util.Queue;

public class TestOneProducerAndOneConsumer {
	public static void main(String[] args) {
		MessageQueue buffer = new SynchronizedMessageQueue();
		Consumer consumer = new Consumer(buffer);
		Producer producer = new Producer(buffer);
		consumer.start();
		producer.start();
	}
}

