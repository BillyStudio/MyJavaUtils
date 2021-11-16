package cn.wsd.utils.designpattern.producerconsumer;

public class Consumer extends Thread {
	private MessageQueue buffer;

	public Consumer(MessageQueue buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		try {
			while(true) {
				int val = (int) buffer.remove();
				System.out.println("consume "+val);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
