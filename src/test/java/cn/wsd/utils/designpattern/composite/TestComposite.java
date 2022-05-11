package cn.wsd.utils.designpattern.composite;

public class TestComposite {
	public static void main(String[] args) {
		ConcreteCollege university = new ConcreteCollege("北京校本部");
		university.Add(new TeachDepartment("学校教务处"));
		university.Add(new FinanceDepartment("学校财务处"));

		ConcreteCollege TianjinCollege = new ConcreteCollege("天津分校");
		TianjinCollege.Add(new TeachDepartment("天津分校教务处"));
		TianjinCollege.Add(new FinanceDepartment("天津分校财务处"));

		university.Add(TianjinCollege);

		ConcreteCollege GuanzhuangCollege = new ConcreteCollege("管庄校区");
		GuanzhuangCollege.Add(new TeachDepartment("管庄校区教务科"));

		university.Add(GuanzhuangCollege);

		System.out.println("结构图");
		university.Display(1);
	}
}
