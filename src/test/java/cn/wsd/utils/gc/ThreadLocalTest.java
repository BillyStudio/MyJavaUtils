package cn.wsd.utils.gc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.ref.WeakReference;
import java.util.HashSet;

public class ThreadLocalTest {
    
    @Data
    static class SimpleDTO {
        Long id;
        HashSet<Integer> set = new HashSet<>();
        
        public SimpleDTO(long id) {
            this.id = id;
        }
    }
    
    static void addSet(SimpleDTO simpleDTO, WeakReference<SimpleDTO> weakReference) {
        SimpleDTO simpleDTO2 = weakReference.get();
        simpleDTO2.getSet().add(1);
        simpleDTO2.getSet().add(2);
        simpleDTO.setSet(simpleDTO2.getSet()); 
    } 

    public static void main(String[] args) {
        // 强引用
        SimpleDTO simpleDTO = new SimpleDTO(1);
        
        // 弱引用
        WeakReference<SimpleDTO> weakSimpleDTO = new WeakReference<>(new SimpleDTO(2));
        addSet(simpleDTO, weakSimpleDTO);

        System.gc();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (simpleDTO.getSet() == null) {
            System.out.println("强引用已被回收");
        } else {
            System.out.println("强引用未被回收");
            System.out.println(simpleDTO.getSet().toString());
        }
        if (weakSimpleDTO.get() == null) {
            System.out.println("弱引用已被回收");
        } else {
            System.out.println("弱引用未被回收");
        }
    }
}
