package com.mycompany.performance;

import java.util.ArrayList;
import java.util.List;

public class NonStaticClassMemoryLeakDemo {

    public static void main(String args[]) {
        List al = new ArrayList<>();
        int counter = 1;
        while (counter <= 1000) {
            OuterClass.InnerClass innerClass = new OuterClass().new InnerClass();
            innerClass.printFromInnerClass(counter);
            al.add(innerClass);
            counter++;
        }
    }
}

class OuterClass {
    private int[] array = new int[(int) 1E6];

    class InnerClass {
        public void printFromInnerClass(int count) {
            System.out.println("printFromInnerClass                    method call count: " + count);
        }
    }
}



