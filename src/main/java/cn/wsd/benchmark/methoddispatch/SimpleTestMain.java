package cn.wsd.benchmark.methoddispatch;

import cn.wsd.benchmark.Counter;
import cn.wsd.benchmark.functionalinterface.CounterImpl;

public class SimpleTestMain {
    private byte[] inBytes = {0, 1};
    private Counter coder = new CounterImpl(6);

    int func() {
        return coder.work(inBytes);
    }

    public static void main(String[] args) {
        SimpleTestMain testObj = new SimpleTestMain();
        int work = testObj.func();
        System.out.println("work=" + work);
    }
}
