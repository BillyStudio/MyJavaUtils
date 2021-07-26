package cn.wsd.utils.designpattern.singleton;

public class InternalEnumSingleton {
	private InternalEnumSingleton() {}

	public static InternalEnumSingleton getInstance() {
		return InternalEnum.INSTANCE.getInstance();
	}

	private enum InternalEnum {
		INSTANCE;

		private InternalEnumSingleton singleton;

		InternalEnum() {
			singleton = new InternalEnumSingleton();
		}

		public InternalEnumSingleton getInstance() {
			return singleton;
		}
	}

	public void printMyself() {
		System.out.println("Hello, I'm " + InternalEnum.INSTANCE.getInstance());
	}
}
