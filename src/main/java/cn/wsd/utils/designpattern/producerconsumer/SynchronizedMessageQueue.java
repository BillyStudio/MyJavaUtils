package cn.wsd.utils.designpattern.producerconsumer;

import java.util.LinkedList;

public class SynchronizedMessageQueue<E> implements MessageQueue<E> {

	private LinkedList<E> queue = new LinkedList<>();
	private int size = 5;

	public SynchronizedMessageQueue() {}

	public SynchronizedMessageQueue(int size) {
		this.size = size;
	}

	@Override
	public synchronized boolean add(E val) {
		try {
			if (queue.size() >= size) {
				wait();    // 阻塞生产者，不让其继续生产
			}
			queue.add(val);
			notify();    // 通知消费者去消费
		} catch (InterruptedException ex) {
			return false;
		}
		return true;
	}

	@Override
	public synchronized E remove() {
		E val = null;
		try {
			if (queue.size() == 0) {
				wait();
			}
			val = queue.poll();
			notify();
		} catch (InterruptedException ex) {
			return null;
		}
		return val;
	}

	@Override
	public E front() {
		return queue.peek();
	}
}
