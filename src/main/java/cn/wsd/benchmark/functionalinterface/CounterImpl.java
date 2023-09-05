package cn.wsd.benchmark.functionalinterface;

import cn.wsd.benchmark.Counter;
import org.openjdk.jmh.annotations.CompilerControl;

public class CounterImpl implements Counter {

	private int offset;

	public CounterImpl(int offset) {
		this.offset = offset;
	}

	@Override
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public int work(byte[] data) {
		return data.length + offset;
	}
}
