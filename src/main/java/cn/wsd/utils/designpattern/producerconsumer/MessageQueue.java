package cn.wsd.utils.designpattern.producerconsumer;

public interface MessageQueue<E> {
	// 向队尾添加元素
	boolean add(E e);

	// 去除队首元素并返回改元素
	E remove();

	// 获取队首元素不去除
	E front();
}
