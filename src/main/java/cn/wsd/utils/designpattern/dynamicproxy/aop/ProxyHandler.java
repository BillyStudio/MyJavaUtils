package cn.wsd.utils.designpattern.dynamicproxy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
	private Object object;

	public ProxyHandler(Object object) {
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("前置切面");
		if (method.getName().equals("request")) {
			method.invoke(this.object, args);
			System.out.println("后置切面");
			return proxy;
		} else if (method.getName().equals("getTime")) {
			Object time = method.invoke(this.object, args);
			System.out.println("后置切面");
			return time;
		} else {
			method.invoke(this.object, args);
			System.out.println("后置切面");
			return "hello world"; // return null 或任意一个对象都是可以的
		}
	}
}
