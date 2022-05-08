package cn.wsd.utils.designpattern.publishsubscribe;

public interface IPublisher<M> {
	void publish(SubscribePublish subscribePublish, M message, boolean isInstantMsg);
}
