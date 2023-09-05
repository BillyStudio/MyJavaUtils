package cn.wsd.benchmark.functionalinterface;

import cn.wsd.benchmark.Counter;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class FunctionalInterfaceTest {

	private int offset = 10;
	private byte[] inBytes = {0, 1};
	private Counter coder = new CounterImpl(offset);

	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public int bynamicMethod(Counter coder, byte[] data) {
		return coder.work(data);
	}

	@Benchmark
	public int dynamicMethodCall() {
		return bynamicMethod(
				(data -> data.length + offset),
				inBytes);
	}

	@Benchmark
	public int normalMethodCall() {
		return coder.work(inBytes);
	}

}
