package cn.wsd.utils.conversion;

import java.util.*;
import java.util.stream.Collectors;

public class ConversionUtil {

	// List转int[]之前是短板，有了stream后比较方便了
	public static int[] List2intArr(List<Integer> input) {
		return input.stream().mapToInt(Integer::intValue).toArray();
	}

	// List::toArray，老功能了，但要注意后面的数组大小分配
	public static Integer[] List2IntegerArr(List<Integer> input) {
		return input.toArray(new Integer[input.size()]);
	}

	// 这里用到了Arrays.stream(int[] arr)的流处理，boxed等效于mapToObj(Integer::valueOf)，即将int转为Integer
	public static List<Integer> intArr2List(int[] input) {
		return Arrays.stream(input).boxed().collect(Collectors.toList());
	}

	// Arrays.asList(T... a)
	public static List<Integer> IntegerArr2List(Integer[] input) {
		return Arrays.asList(input);
	}

	// 还是用流处理，体会到Java8流处理的强大之处了吧！注意和intArr2List的对比
	public static int[] IntegerArr2intArr(Integer[] input) {
		return Arrays.stream(input).mapToInt(Integer::intValue).toArray();
	}

	// 不推荐使用Integer[]，故此方法和List2IntegerArr应避免使用。不过上面List2IntegerArr的方法可以借鉴来做List<T>到T[]的通用转化
	public static Integer[] intArr2IntegerArr(int[] input) {
		return Arrays.stream(input).boxed().toArray(Integer[]::new);
	}

}
