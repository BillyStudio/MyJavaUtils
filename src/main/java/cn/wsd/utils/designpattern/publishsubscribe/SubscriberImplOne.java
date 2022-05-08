package cn.wsd.utils.designpattern.publishsubscribe;

public class SubscriberImplOne<M> implements ISubscriber<M> {
	String name;

	public SubscriberImplOne(String subscriberName) {
		name = subscriberName;
	}

	@Override
	public void subscribe(SubscribePublish subscribePublish) {
		subscribePublish.subscribe(this);
	}

	@Override
	public void unsubscribe(SubscribePublish subscribePublish) {
		subscribePublish.unSubscribe(this);
	}

	@Override
	public void update(String publisher, M message) {
		System.out.println(name + "收到" + publisher + "发来的消息：" + message.toString());
	}
}
