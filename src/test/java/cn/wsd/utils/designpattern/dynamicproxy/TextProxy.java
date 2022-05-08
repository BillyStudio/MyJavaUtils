package cn.wsd.utils.designpattern.dynamicproxy;

public class TextProxy {
	public static void main(String[] args) {
		ChineseYoungMan user1 = new ChineseYoungMan();
		ChineseUser human = (ChineseUser) UserProxyFactory.getProxyInstance(user1);
		human.show();
		ChineseUser user2 = new ChineseOldMan();
		human = (ChineseUser) UserProxyFactory.getProxyInstance(user2);
		human.show();
	}
}
