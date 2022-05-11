package cn.wsd.utils.designpattern.strategy;

// 返利收费子类
public class CashReturn implements CashSuper {
	private double moneyCondition = 0.0;	// 返利条件
	private double moneyReturn = 0.0;		// 返利值

	public CashReturn(String moneyCondition, String moneyReturn) {
		this.moneyCondition = Double.parseDouble(moneyCondition);
		this.moneyReturn = Double.parseDouble(moneyReturn);
	}

	@Override
	public double acceptCash(double money) {
		double result = money;
		if (money > moneyCondition) {
			result = money - Math.floor(money / moneyCondition) * moneyReturn;
		}
		return result;
	}
}
