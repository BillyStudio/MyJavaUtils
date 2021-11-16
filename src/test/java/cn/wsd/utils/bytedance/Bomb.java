package cn.wsd.utils.bytedance;

import java.util.Scanner;

public class Bomb {

	static int climb(int[] heights, int len, int target) {
		if (len == 1) {
			if (heights[0] >= target) return 0;
			return 1;
		}
		for(int pos=1; pos<len; ++pos) {
			if(heights[pos] <= heights[pos-1]) {
				if (heights[pos-1] >= target) return pos-1;
				return len;
			}
		}
		return len;
	}

	static int left_search(int[] arr, int l, int r, int k) {
		while(arr[l] <= arr[r]) {
			int mid = l + (r-l)/2;
			if(arr[mid] < k) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return l;
	}

	static int right_search(int[] arr, int l, int r, int k) {
		while(arr[l] >= arr[r]) {
			int mid = l + (r-l)/2;
			if(arr[mid] < k) r = mid - 1;
			else l = mid + 1;
		}
		return r;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		for(int i=0; i<m; ++i) {
			int n = scanner.nextInt(); // 传感器数据条数
			int k = scanner.nextInt();	// 目标高度
			int[] heights = new int[n];
			for(int j=0; j<n; ++j) {
				heights[j] = scanner.nextInt();
			}
			int peakPos = climb(heights, n, k);	//找能达到目标高的顶峰位置
			if (peakPos >= n) {
				System.out.println("false");
			} else {
				int left = left_search(heights, 0, peakPos, k);
				int right = right_search(heights, peakPos, n-1, k);
				System.out.printf("true %d %d", left, right);
			}
		}
	}
}
/*
5
9 4
1 2 3 4 5 5 4 3 2
9 14
1 2 3 4 5 5 4 3 2
11 7
1 2 3 4 5 6 7 5 4 3 2
1 14
1
1 1
1
 */
