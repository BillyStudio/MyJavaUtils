package cn.wsd.utils.designpattern.strategy;

public class TestStrategy {
	public static void main(String[] args) {
		CashContext csuper = new CashContext("满300返100");

		double totalPrices = 0;
		totalPrices += csuper.getResult(600);
		System.out.println("收费：" + totalPrices);
	}
}
