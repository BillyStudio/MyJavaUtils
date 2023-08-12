package cn.wsd.benchmark.methoddispatch;

import cn.wsd.benchmark.Coder;

public class Coder1 implements Coder {
    public int work(byte[] data) {
        return data.length; // something light-weight
    }
}