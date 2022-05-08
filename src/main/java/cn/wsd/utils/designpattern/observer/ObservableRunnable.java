package cn.wsd.utils.designpattern.observer;

/**
 * 被监听者
 */
public class ObservableRunnable implements Runnable {

	protected final LifeCycleListener listener;

	private final Runnable task;

	public ObservableRunnable(LifeCycleListener listener, Runnable task) {
		this.listener = listener;
		this.task = task;
	}

	@Override
	public void run() {
		try {
			notifyChange(new RunnableEvent(RunnableState.RUNTIME, Thread.currentThread(), null));
			task.run();
			notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
		} catch (Throwable e) {
			notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), null));
		}
	}

	protected void notifyChange(final RunnableEvent event) {
		listener.onEvent(event);
	}

	public enum RunnableState {
		RUNTIME,
		ERROR,
		DONE
	}

	public static class RunnableEvent {
		private final RunnableState state;
		private final Thread thread;
		private final Throwable cause;

		public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
			this.state = state;
			this.thread = thread;
			this.cause = cause;
		}

		public RunnableState getState() {
			return state;
		}

		public Thread getThread() {
			return thread;
		}

		public Throwable getCause() {
			return cause;
		}
	}
}
