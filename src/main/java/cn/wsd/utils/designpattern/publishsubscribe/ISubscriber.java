package cn.wsd.utils.designpattern.publishsubscribe;

/**
 * 订阅者接口
 * @param <M> 消息类型
 */
public interface ISubscriber<M> {
	void subscribe(SubscribePublish subscribePublish);

	void unsubscribe(SubscribePublish subscribePublish);

	void update(String publisher, M message);
}
