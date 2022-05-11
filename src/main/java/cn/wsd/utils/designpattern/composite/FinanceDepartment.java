package cn.wsd.utils.designpattern.composite;

import java.util.Collections;

public class FinanceDepartment extends College {
	public FinanceDepartment(String name) {
		super(name);
	}

	@Override
	public void Add(College c) {
	}

	@Override
	public void Remove(College c) {
	}

	@Override
	public void Display(int depth) {
		System.out.println(String.join("", Collections.nCopies(depth, "-")) + name);
	}

	@Override
	public void LineOfDuty() {
		System.out.println(name + "财务收支管理");
	}

}
