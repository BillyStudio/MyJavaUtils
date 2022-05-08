package cn.wsd.utils.designpattern.observer;

public interface LifeCycleListener {
	void onEvent(ObservableRunnable.RunnableEvent event);
}
