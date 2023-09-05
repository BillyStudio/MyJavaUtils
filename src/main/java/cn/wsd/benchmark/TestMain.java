package cn.wsd.benchmark;

import java.util.Arrays;
import java.util.stream.Collectors;

import cn.wsd.benchmark.functionalinterface.FunctionalInterfaceTest;
import org.openjdk.jmh.profile.LinuxPerfAsmProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class TestMain {

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(FunctionalInterfaceTest.class.getSimpleName())
				.measurementIterations(5)
				.addProfiler(LinuxPerfAsmProfiler.class)
				.forks(1)
				.build();
		new Runner(opt).run();
	}

}
