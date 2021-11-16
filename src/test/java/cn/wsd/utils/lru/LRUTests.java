package cn.wsd.utils.lru;

import static org.junit.Assert.*;

public class LRUTests {

	public static void main(String[] args) {
		LRU test = new LRU();
		int[][] input = null;
		int k = 0;
		int[] result = test.testLRU(input, k);
		for (int r : result) {
			System.out.println(r);
		}
	}
}
