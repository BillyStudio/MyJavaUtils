package cn.wsd.utils.hashmap;

import cn.wsd.utils.sizeof.ObjectSizeUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {
	public static void main(String[] args) throws IllegalAccessException {
		Map<Integer, String> hashMap = new HashMap<>();
		System.out.println(ObjectSizeUtil.getByteSize(hashMap));
		hashMap.put(100, "a");
		hashMap.put(100, "b");
		hashMap.put(200, "c");
		String v1 = hashMap.get(100);
		hashMap.put(4, "d");
		Set<Integer> intSet = hashMap.keySet();
		boolean ifContain4 = intSet.contains(4);
		System.out.println(ObjectSizeUtil.getFullByteSize(hashMap));
	}
}
