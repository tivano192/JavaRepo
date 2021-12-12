package com.mycompany.performance;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileInputStreamMemoryLeakDemo {

    public static void main(String args[]) {
        try {
            closeInFinally();
            closeUsingTryWithResources();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void closeInFinally() throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("file.txt");
            int data = inputStream.read();
            while (data != -1) {
                System.out.print((char) data);
                data = inputStream.read();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private static void closeUsingTryWithResources() throws IOException {
        try (InputStream inputStream = new FileInputStream("file.txt")) {
            int data = inputStream.read();
            while (data != -1) {
                System.out.print((char) data);
                data = inputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
