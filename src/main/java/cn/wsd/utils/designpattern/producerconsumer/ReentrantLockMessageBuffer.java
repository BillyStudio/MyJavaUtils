package cn.wsd.utils.designpattern.producerconsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMessageBuffer<E> implements MessageQueue<E> {
	private final Lock lock = new ReentrantLock();
	// 指示生产者线程队列是否已满
	private final Condition notFull = lock.newCondition();
	// 指示消费者线程队列是否为空
	private final Condition notEmpty = lock.newCondition();

	// 缓冲区容量
	private int capacity;
	private List<E> list = new LinkedList<>();

	public ReentrantLockMessageBuffer(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public boolean add(E val) {
		lock.lock();
		try {
			while (list.size() == capacity) {
				try {
					System.out.println("the list is full...");
					notFull.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
					return false;
				}
			}
			list.add(val);
			System.out.println("producer--" + Thread.currentThread().getName()
					+ "--put:" + val + "====size:" + list.size());
			notEmpty.signalAll();	// 唤醒所有消费者线程
		} finally {
			lock.unlock();
		}
		return true;
	}

	@Override
	public E remove() {
		lock.lock();
		try {
			while (list.size() == 0) {
				try {
					System.out.println("the list is empty...");
					notEmpty.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
					return null;
				}
			}
			E val = list.remove(0);
			System.out.println("consumer--" + Thread.currentThread().getName()
				+ "--take:" + val + "====size:" + list.size());
			notFull.signalAll();	// 唤醒所有生产者线程
			return val;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E front() {
		return null;
	}
}
