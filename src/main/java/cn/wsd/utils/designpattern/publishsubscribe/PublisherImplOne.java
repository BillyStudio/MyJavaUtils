package cn.wsd.utils.designpattern.publishsubscribe;

/**
 * 发布者实现类
 * @param <M>
 */
public class PublisherImplOne<M> implements IPublisher<M> {
	private String name;

	public PublisherImplOne(String publisherName) {
		super();
		name = publisherName;
	}

	@Override
	public void publish(SubscribePublish subscribePublish, M message, boolean isInstantMsg) {
		subscribePublish.publish(name, message, isInstantMsg);
	}

}
