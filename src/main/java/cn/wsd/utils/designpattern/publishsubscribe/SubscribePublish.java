package cn.wsd.utils.designpattern.publishsubscribe;


import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 订阅器类
 * @param <M> 消息类型
 */
public class SubscribePublish<M> {
	// 订阅器名称
	private String name;
	// 订阅器队列容量
	final int QUEUE_CAPACITY = 20;
	// 订阅器存储队列
	private BlockingQueue<Msg> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
	// 订阅者
	private List<ISubscriber> subscribers = new ArrayList<>();

	public SubscribePublish(String name) {
		this.name = name;
	}

	public void publish(String publisher, M message, boolean isInstantMsg) {
		if (isInstantMsg) {
			update(publisher, message);
			return ;
		}
		Msg<M> m = new Msg<>(publisher, message);
		if (!queue.offer(m)) {
			update();
		}
	}

	public void subscribe(ISubscriber subscriber) {
		subscribers.add(subscriber);
	}

	public void unSubscribe(ISubscriber subscriber) {
		subscribers.remove(subscriber);
	}

	public void update() {
		Msg m = null;
		while((m = queue.peek()) != null) {
			this.update(m.getPublisher(), (M) m.getMsg());
		}
	}

	public void update(String publisher, M Msg) {
		for (ISubscriber subscriber : subscribers) {
			subscriber.update(publisher, Msg);
		}
	}

}

class Msg<M> {
	private String publisher;
	private M m;

	public Msg(String publisher, M m) {
		this.publisher = publisher;
		this.m = m;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public M getMsg() {
		return m;
	}

	public void setMsg(M m) {
		this.m = m;
	}
}

