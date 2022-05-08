package cn.wsd.utils.designpattern.dynamicproxy;

/*
	这是一个动态代理调用的模拟接口，该接口的实现可以是一个本地方法（如ChineseUserImpl所示），
	更多的情况下是一个远程方法，即通过转化为http报文发出
 */
public interface ChineseUser {

	void show();
}
