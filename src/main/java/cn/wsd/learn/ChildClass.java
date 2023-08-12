package cn.wsd.learn;

import java.util.ArrayList;
import java.util.List;

public class ChildClass extends ParentClass {

    public double invokemethodB(InterfaceB method) {
        return method.imethodB();
    }
    
    public static void main(String[] args) {
        ChildClass child = new ChildClass();
        List<Integer> list = new ArrayList<>(400);
        Integer res = child.imethod();
        for (int i = 0; i<400; ++i) {
            list.add(res);
        }
//        double pi = child.invokemethodB(() -> 3.14);
    }
}
