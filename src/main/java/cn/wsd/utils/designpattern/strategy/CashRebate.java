package cn.wsd.utils.designpattern.strategy;

// 打折收费子类
public class CashRebate implements CashSuper {
	private double moneyRebate = 1.0;

	// 构造函数中输入费率
	public CashRebate(String moneyRebate) {
		this.moneyRebate = Double.parseDouble(moneyRebate);
	}

	@Override
	public double acceptCash(double money) {
		return money * moneyRebate;
	}
}
