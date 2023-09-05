package cn.wsd.benchmark.methoddispatch;

import cn.wsd.benchmark.Counter;

public class Coder0 extends AbstractCoder implements Counter {
    public static int staticWork(byte[] data) {
        return data.length;
    }

    @Override
    public int work(byte[] data) {
        return data.length;
    }

    @Override
    public int abstractWork(byte[] data) {
        return data.length;
    }
}
