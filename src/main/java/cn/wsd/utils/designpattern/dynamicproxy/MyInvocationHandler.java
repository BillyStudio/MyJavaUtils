package cn.wsd.utils.designpattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	private Object obj; // 需要使用被代理类对象初始化

	public void bind(Object obj) {
		this.obj = obj;
	}

	/*
	当通过代理类的对象调用方法a时，就会动态转为调用如下的invoke方法
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// method即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
		return method.invoke(obj, args);
	}
}
