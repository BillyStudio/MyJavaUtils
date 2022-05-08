package cn.wsd.utils.designpattern.observer;

import java.util.Arrays;
import java.util.List;

public class ThreadLifeCycleClient {
	public static void main(String[] args) {
		List<String> ids = Arrays.asList("1", "2", "3", "4");
		ThreadLifeCycleObserver observer = new ThreadLifeCycleObserver();
		ids.forEach(name -> new Thread(new ObservableRunnable(observer, ()-> {
			System.out.println("当前任务正在执行一些操作");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}), name).start());
	}
}
