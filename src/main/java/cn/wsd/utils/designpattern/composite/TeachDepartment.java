package cn.wsd.utils.designpattern.composite;

import java.util.Collections;

// 教务处
class TeachDepartment extends College {
	public TeachDepartment(String name) {
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
		System.out.println(name + "学生教学管理");
	}
}
