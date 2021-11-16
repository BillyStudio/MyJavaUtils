package cn.wsd.utils.bytedance;
/* n=4个订单 m=查第3个订单的实际区餐顺序
1 2 3 4
输出： 3
7 3
8 7 8 9 1 2 8
输出： 4
m <= 1000
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Ordering {
	public static void main(String[] args) throws FileNotFoundException {
		/* 调试用 */
		Scanner sc = new Scanner(System.in);
		// end
		while(sc.hasNext()) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			if(n < m) {System.out.println("-1");continue;}
			int[] arr = new int[n];
			boolean[] visited = new boolean[n];
			for(int i=0; i<n; ++i) {
				arr[i] = sc.nextInt();
				visited[i] = false;
			}
			int base = 0;
			for(int j=0; j<n; ++j) {
				int minIdx = 0;
				int min = Integer.MAX_VALUE;
				for(int i=base; i<base+n; ++i) if(!visited[i%n]) {
					if(arr[i%n] < min) {
						min = arr[i%n];
						minIdx = i%n;
					}
				}
				if(minIdx == m-1) {
					System.out.println(j+1);
					break;
				}
				visited[minIdx] = true;
				base = minIdx;
			}
		}
	}
}
