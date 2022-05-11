package cn.wsd.utils.designpattern.composite;

abstract class College {
	protected String name;

	public College(String name) {
		this.name = name;
	}

	public abstract void Add(College c);	// 增加
	public abstract void Remove(College c);	// 移除
	public abstract void Display(int depth);	// 显示
	public abstract void LineOfDuty();		// 履行职责，不同的部门需履行不同的职责
}
