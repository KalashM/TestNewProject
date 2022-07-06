package com.example.files;

import java.io.File;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {

        /*File[] matchedFiles = MyFiles.findFilesByPattern("D:\\", "(.*)fo(.*)");
        for (File matchedFile : matchedFiles) {
            System.out.println(matchedFile.getName());
        }*/

        File in = new File("D:\\Java\\books\\Bloch_Effective-Java-3rd.pdf");
        File out = new File("D:\\Java\\books\\COPY_Bloch_Effective-Java-3rd.pdf");
        File outBuffered = new File("D:\\Java\\books\\COPYBuffered_Bloch_Effective-Java-3rd.pdf");

        long start = System.nanoTime();
        MyFiles.copyFileUsingStream(in, out);
        System.out.println("Time taken to copy file using Stream = " + (System.nanoTime() - start));

        start = System.nanoTime();
        MyFiles.copyFileUsinfBufferedStream(in, outBuffered);
        System.out.println("Time taken to copy file using Buffered Stream = " + (System.nanoTime() - start));
    }
}
