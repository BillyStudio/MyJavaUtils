package cn.wsd.utils.combinatorics;

public class Rolls {

	int[] dp;

public int rollToTarget(int N) {
	dp = new int[32];
	dp[1] = 1;
	for(int i=2; i<=N; ++i) {
		if(i <= 6) {
			dp[i] = dp[i-1]*2;
		} else if(i==7){
			dp[i] = 2^i - 1;
		} else if(i==8) {
			dp[i] = 2^i - 3;
		} else if(i==9) {
			dp[i] = 2^i - 8;
		} else if(i==10) {
			dp[i] = 2^i - 16;
		}
	}
	return dp[N];
}

	public static void main(String[] args) {
		Rolls test = new Rolls();
		System.out.println(test.rollToTarget(4));
	}
}
