package cn.wsd.utils.designpattern.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ConcreteCollege extends College {
	private List<College> children = new ArrayList<>();

	public ConcreteCollege(String name) {
		super(name);
	}

	@Override
	public void Add(College c) {
		children.add(c);
	}

	@Override
	public void Remove(College c) {
		children.remove(c);
	}

	@Override
	public void Display(int depth) {
		System.out.println(String.join("", Collections.nCopies(depth, "-")) + name);
		for (College e : children) {
			e.Display(depth + 2);
		}
	}

	@Override
	public void LineOfDuty() {
		for (College c : children) {
			c.LineOfDuty();
		}
	}
}
