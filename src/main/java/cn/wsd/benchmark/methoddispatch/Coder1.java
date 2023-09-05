package cn.wsd.benchmark.methoddispatch;

import cn.wsd.benchmark.Counter;

public class Coder1 implements Counter {
    public int work(byte[] data) {
        return data.length; // something light-weight
    }
}
