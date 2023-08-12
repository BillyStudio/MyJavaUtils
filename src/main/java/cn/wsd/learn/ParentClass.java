package cn.wsd.learn;

import java.util.Random;

public class ParentClass implements InterfaceA {
    
    private static int seed = 0;

    @Override
    public Integer imethod() {
        return seed++;
    }
}
