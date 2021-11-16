package cn.wsd.utils.linklist;

public class TestLinkList {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		LinkList list = new LinkList(arr);
		list.remove(list.size() - 1);
		list.remove(0);
		System.out.println(list.toString());
	}
}
