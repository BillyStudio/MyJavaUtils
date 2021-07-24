package cn.wsd.utils.hashmap;

import java.util.HashMap;

public class HashMapTest {
	public static void main(String[] args) {
		HashMap<Integer, Boolean> hashMap = new HashMap<>();
		hashMap.put(1, true);
		hashMap.put(1, false);
	}
}
