package cn.wsd.utils.designpattern.strategy;

// 正常收费子类
public class CashNormal implements CashSuper {

	@Override
	public double acceptCash(double money) {
		return money;
	}
}
