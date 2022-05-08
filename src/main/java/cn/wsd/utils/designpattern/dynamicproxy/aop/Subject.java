package cn.wsd.utils.designpattern.dynamicproxy.aop;

/**
 * 代理模式中的接口
 */
public interface Subject {

	/**
	 * 方法无具体含义，为了测试参数及返回值类型
	 */
	Subject request(String name);

	/**
	 * 方法无具体含义，为了测试方法的返回值
	 * @return
	 */
	String getTime();

	/**
	 * 方法无具体含义，为了测试方法无返回值
	 */
	void test();
}
