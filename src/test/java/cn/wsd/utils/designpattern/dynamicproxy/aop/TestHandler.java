package cn.wsd.utils.designpattern.dynamicproxy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestHandler {
	public static void main(String[] args) {
		Subject subject = new SubjectImpl();
		InvocationHandler handler = new ProxyHandler(subject);
		Class<?> clazz = subject.getClass();
		Subject proxy = (Subject) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
		System.out.println(proxy.request("name").request("yes").getTime());
		proxy.test();
		System.out.println("代理类的类型为：" + Proxy.getProxyClass(clazz.getClassLoader(), clazz.getInterfaces()));
		System.out.println("proxy是否为代理类型？" + Proxy.isProxyClass(proxy.getClass()));
		System.out.println("proxy的调用处理程序为：" + Proxy.getInvocationHandler(proxy));
	}
}
