package cn.wsd.utils.designpattern.observer;

public class ThreadLifeCycleObserver implements LifeCycleListener {

	private final Object LOCK = new Object();

	@Override
	public void onEvent(ObservableRunnable.RunnableEvent event) {
		synchronized (LOCK) {
			System.out.println("The runnable (" + event.getThread().getName() + ") date changed and state is " + event.getState());
			if (event.getCause() != null) {
				System.out.println("The runnable (" + event.getThread().getName() + ") process fail");
				event.getCause().printStackTrace();
			}
		}
	}
}
