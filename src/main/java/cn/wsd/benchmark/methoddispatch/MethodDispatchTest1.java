package cn.wsd.benchmark.methoddispatch;

import cn.wsd.benchmark.Data;
import cn.wsd.benchmark.JMHSample_15_Asymmetric;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.LinuxPerfAsmProfiler;
import org.openjdk.jmh.profile.WinPerfAsmProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class MethodDispatchTest1 {
    @Param("10000")
    private int count;

    private Data[] datas;

    @Setup
    public void setup() {
        Random r = new Random();

        datas = new Data[count];
        for (int c = 0; c < count; c++) {
            byte[] contents = new byte[10];
            r.nextBytes(contents);

            datas[c] = new Data((byte) 0, contents);
        }
    }

    @Benchmark
    public void static_Ref() {
        Data[] l = datas;
        int c = count;
        for (int i = 0; i < c; i++) {
            l[i].do_Static_Ref();
        }
    }

    @Benchmark
    public void dynamic_Interface_Ref() {
        Data[] l = datas;
        int c = count;
        for (int i = 0; i < c; i++) {
            l[i].do_Dynamic_Interface_Ref();
        }
    }

    @Benchmark
    public void dynamic_Abstract_Ref() {
        Data[] l = datas;
        int c = count;
        for (int i = 0; i < c; i++) {
            l[i].do_Dynamic_Abstract_Ref();
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MethodDispatchTest1.class.getSimpleName())
                //     使用之前要安装hsdis
                //-XX:-TieredCompilation 关闭分层优化 -server
                //-XX:+LogCompilation  运行之后项目路径会出现按照测试顺序输出hotspot_pid<PID>.log文件,可以使用JITWatch进行分析,可以根据最后运行的结果的顺序按文件时间找到对应的hotspot_pid<PID>.log文件
//                .jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+LogCompilation", "-XX:+TraceClassLoading", "-XX:+PrintAssembly")
                //  .addProfiler(CompilerProfiler.class)    // report JIT compiler profiling via standard MBeans
                //  .addProfiler(GCProfiler.class)    // report GC time
                // .addProfiler(StackProfiler.class) // report method stack execution profile
                // .addProfiler(PausesProfiler.class)
                .addProfiler(LinuxPerfAsmProfiler.class)
                /*
                WinPerfAsmProfiler
                You must install Windows Performance Toolkit. Once installed, locate directory with xperf.exe file
                and either add it to PATH environment variable, or set it to jmh.perfasm.xperf.dir system property.
                 */
                //.addProfiler(WinPerfAsmProfiler.class)
                //更多Profiler,请看JMH介绍
                //.output("InstructionsBenchmark.log")//输出信息到文件
                .build();
        new Runner(opt).run();
    }
}



