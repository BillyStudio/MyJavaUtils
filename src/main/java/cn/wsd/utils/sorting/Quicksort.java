package cn.wsd.utils.sorting;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Quicksort {
	public int sort(int[] num,int i,int j) {
		int k=i;
		while(i<j){
			while(i<j&&num[j]>num[k]){
				j--;
			}
			while(i<j&&num[i]<num[k]){
				i++;
			}
			swap(num,i,j);
		}
		swap(num,k,i);
		return i;
	}
	public void swap(int[] num,int i,int j) {
		int temp= num[i];
		num[i]=num[j];
		num[j]=temp;
	}

	public void quick(int[] num,int i,int j) {
		int index=sort(num,i,j);
		if(i<j){
			quick(num,i,index-1);
			quick(num,index+1,j);
		}
	}

	public static void main(String[] args) {
		int[] num = {5, 4, 3, 2, 1};
		Quicksort test = new Quicksort();
		test.quick(num, 0, num.length-1);
		System.out.println(Arrays.stream(num).mapToObj(Objects::toString).collect(Collectors.joining(",")));
	}
}
