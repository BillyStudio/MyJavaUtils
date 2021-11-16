package cn.wsd.utils.linklist;

public class LinkList {
	public ListNode head;
	public int length;

	public LinkList(int[] initArr) {
		head = new ListNode();
		length = initArr.length;
		if (length == 0) return;
		head.val = initArr[0];
		ListNode node = head;
		for (int i = 1; i < length; ++i) {
			ListNode temp = new ListNode();
			temp.val = initArr[i];
			node.next = temp;
			node = node.next;
		}
	}

	public boolean remove(int index) {
		if(index >= length) return false;
		ListNode node = head;
		while(index-- > 0) {
			node = node.next;
		}
		if (node.next == null) { // 最后一个节点
			node = null; // let GC clean
		} else {
			node.val = node.next.val;
			node.next = node.next.next;
		}
		length --;
		return true;
	}

	public int size() {
		return length;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LinkList[");
		ListNode node = head;
		for(int i = 0; i < length; ++i,node=node.next) {
			builder.append(node.val);
			if (i < length - 1) {
				builder.append(',');
			} else {
				builder.append(']');
			}
		}
		return builder.toString();
	}
}
