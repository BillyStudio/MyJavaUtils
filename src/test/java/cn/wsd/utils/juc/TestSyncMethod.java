package cn.wsd.utils.juc;

public class TestSyncMethod {

	public static void main(String[] args) {
		SyncMethod syncMethod = new SyncMethod();
		new Thread(() -> {
			syncMethod.incAndGet3();
			System.out.println(Thread.currentThread().getName() + ": " + syncMethod.getValue());
		}, "t3").start();
//		new Thread(() -> {
//			SyncMethod syncMethod = new SyncMethod();
//			syncMethod.incAndGet1();
//			System.out.println(Thread.currentThread().getName() + ": " + syncMethod.getValue());
//		}, "t2").start();
		new Thread(() -> {
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			syncMethod.incAndGet0();
			System.out.println(Thread.currentThread().getName() + ": " + syncMethod.getValue());
		}, "t0").start();
	}
}
