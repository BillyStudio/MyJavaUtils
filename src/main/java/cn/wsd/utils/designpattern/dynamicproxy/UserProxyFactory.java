package cn.wsd.utils.designpattern.dynamicproxy;

import java.lang.reflect.Proxy;

public class UserProxyFactory {
	public static Object getProxyInstance(Object obj) {
		MyInvocationHandler myHandler = new MyInvocationHandler();
		myHandler.bind(obj);
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), myHandler);
	}
}
