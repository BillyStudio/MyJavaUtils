package cn.wsd.utils.designpattern.strategy;

public class CashContext {
	private CashSuper cs;

	// 构造方法传入收费类型以产生具体的收费策略
	public CashContext(String type) {
		switch (type) {
		case "正常收费":
			CashNormal cs0 = new CashNormal();
			cs = cs0;
			break;
		case "满300返100":
			CashReturn cr1 = new CashReturn("300", "100");
			cs = cr1;
			break;
		case "打8折":
			CashRebate cr2 = new CashRebate("0.8");
			cs = cr2;
			break;
		}
	}

	// 根据收费策略的不同，获得计算结果
	public double getResult(double money) {
		return cs.acceptCash(money);
	}
}
