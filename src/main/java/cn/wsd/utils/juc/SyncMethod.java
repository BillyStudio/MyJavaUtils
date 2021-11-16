package cn.wsd.utils.juc;

public class SyncMethod {
	private int value = 0;
	private final Object mutex = new Object();

	public synchronized int incAndGet0() {
		return ++value;
	}

	public int incAndGet1() {
		synchronized(this){
			try {
				Thread.sleep(3000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			return ++value;
		}
	}

	public int incAndGet2() {
		synchronized(SyncMethod.class){
			return ++value;
		}
	}

	public int incAndGet3() {
		synchronized(mutex){
			try {
				Thread.sleep(2000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			return ++value;
		}
	}

	public int getValue() {
		return value;
	}
}
