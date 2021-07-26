package cn.wsd.utils.designpattern.singleton;

import static org.junit.Assert.*;

public class InternalEnumSingletonTests {

	public static void main(String[] args) {
		InternalEnumSingleton singleton = InternalEnumSingleton.getInstance();
		singleton.printMyself();
	}

}
