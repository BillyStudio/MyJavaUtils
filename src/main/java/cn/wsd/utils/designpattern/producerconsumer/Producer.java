package cn.wsd.utils.designpattern.producerconsumer;

public class Producer extends Thread {
	private MessageQueue buffer;

	public Producer(MessageQueue buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		for(int i = 0; i < 10; ++i) {
			try {
				buffer.add(i);
				Thread.sleep((int) Math.random()*100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
