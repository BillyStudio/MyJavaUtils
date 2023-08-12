package cn.wsd.utils.sizeof;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class ObjectSizeTest {
    
    private static int testSize = 1000;

    private static Map<String, NeedDumpInfo> scene2NextDumpTime = new ConcurrentHashMap<>(testSize);

    @Data
    @AllArgsConstructor
    public static class NeedDumpInfo {
        int remainNum;
        long leastDumpTime;
        
        public NeedDumpInfo() {}
    }
    
    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    static class CpNeedDump extends NeedDumpInfo {
        NeedDumpInfo[] arr;
        
        public CpNeedDump(int remainNum, long leastDumpTime, NeedDumpInfo[] arr) {
            super(remainNum, leastDumpTime);
            this.arr = arr;
        }
    }
    
    
    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("before byte size=" + ObjectSizeUtil.getFullByteSize(scene2NextDumpTime));
        Random rand = new Random();
        for (int i = 0; i < testSize; i++) {
            int length = 100;
            boolean useLetters = true;
            boolean useNumbers = true;
            String randomString = RandomStringUtils.random(length, useLetters, useNumbers);
            scene2NextDumpTime.put(randomString, new NeedDumpInfo(rand.nextInt(), rand.nextLong()));
        }
        String s = "To be, or not to be, that is the question";
//        System.out.println("single char String size=" + ObjectSizeUtil.getByteSize(s.toCharArray()));
        float f = 1.0f;
        System.out.println("standard float size=" + ObjectSizeUtil.getByteSize(f));
        NeedDumpInfo needDumpInfo = new NeedDumpInfo(rand.nextInt(100), rand.nextLong());
        System.out.println("NeedDumpInfo full size=" + ObjectSizeUtil.getFullByteSize(needDumpInfo));
        NeedDumpInfo[] needDumpInfos = new NeedDumpInfo[2];
        needDumpInfos[0] = needDumpInfo;
        needDumpInfos[1] = new NeedDumpInfo(rand.nextInt(100), rand.nextLong());
        CpNeedDump cpNeedDump = new CpNeedDump(rand.nextInt(100), rand.nextLong(), needDumpInfos);
//        System.out.println("Cp NeedDumpInfo full size=" + ObjectSizeUtil.getFullByteSize(cpNeedDump));
        System.out.println("after byte size=" + ObjectSizeUtil.getFullByteSize(scene2NextDumpTime));
//        Scanner scanner = new Scanner(System.in);
//        scanner.nextLine();
        long start = System.currentTimeMillis();
        scene2NextDumpTime.clear();
        System.out.println("clear cost time = " + (System.currentTimeMillis() - start) + " ms");
    }
    
}
