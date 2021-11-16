package cn.wsd.utils.sorting;

import java.util.*;
import java.util.stream.Collectors;

public class LeastKNumbers {
	public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
		ArrayList<Integer> res = new ArrayList<>();
		if(input.length < k || k <= 0) { return res; }
		int left = 0, right = input.length-1;
		while(left < right) {
			int K = partition(input, left, right);
			if(K == k-1) {
				break;
			} else if(K > k-1) {
				right = K-1;
			} else { // K < k
				left = K+1;
			}
		}
		for(int i=0; i<k; ++i) {
			res.add(input[i]);
		}
		return res;
	}

	int partition(int[] input, int l, int r) {
		int pivot = input[l];
		int i = l, j = r;
		while (l < r) {
			while(j > l && input[j] >= pivot) {--j;}
			while(i < r && input[i] <= pivot) {++i;}
			if(i >= j) { break;}
			swap(input, i, j);
		}
		swap(input, l, j);
		return j;
	}

	void swap(int[] arr, int i, int j) {
		if(i != j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	public static void main(String[] args) {
		LeastKNumbers test = new LeastKNumbers();
		int[] input = {3,1,5,7,8,4,6,2};
		List<Integer> res = test.GetLeastNumbers_Solution(input, 4);
		System.out.println(res.stream().map(Objects::toString).collect(Collectors.joining(",")));
	}
}
