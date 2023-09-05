package cn.wsd.benchmark.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.profile.LinuxPerfAsmProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class CollectToSetTest {
	private int offset = 10;
	private int[] array = {3,2,1,6,7,8,12,15,5};

	@Benchmark
	public List<Integer> dynamicMethodCall() {
		return Arrays.stream(array).mapToObj(i -> i + offset).collect(Collectors.toList());
	}

	@Benchmark
	public List<Integer> normalMethodCall() {
		List<Integer> result = new ArrayList<>(10);
		for (int a : array) {
			result.add(a + offset);
		}
		return result;
	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(CollectToSetTest.class.getSimpleName())
				.measurementIterations(5)
				.addProfiler(LinuxPerfAsmProfiler.class)
				.forks(1)
				.build();
		Arrays.stream(args).collect(Collectors.toSet());
		new Runner(opt).run();
	}
}
