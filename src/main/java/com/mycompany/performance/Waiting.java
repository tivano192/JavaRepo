package com.mycompany.performance;

public class Waiting {

    private Waiting(){
    }

    public static void pause() {
        try {
            Thread.currentThread().sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
