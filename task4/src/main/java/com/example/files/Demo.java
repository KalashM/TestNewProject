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
        File outChannel = new File("D:\\Java\\books\\COPYChannel_Bloch_Effective-Java-3rd.pdf");
        File outFiles = new File("D:\\Java\\books\\COPYFiles_Bloch_Effective-Java-3rd.pdf");

        String myURLMov = "https://drive.google.com/file/d/1UU2S7E_8LgLzdzGRg428uMX3FgHXnCtg/view?usp=sharing";
        String myURLJpg = "https://drive.google.com/file/d/1lL84rFawrFDy4oUyIwohAF6uaEWFbhjL/view?usp=sharing";
        String myURLTxt = "https://drive.google.com/file/d/1ie988yuZL9sj5Mfy4m8dpXp7MacpIQtZ/view";
        String outPathMov = "D:\\Java\\books\\JAVA-FHD0072.MOV";
        String outPathJpg = "D:\\Java\\books\\SomePicture.jpg";
        String outPathTxt = "D:\\Java\\books\\SomeTxt.txt";

        long start = System.nanoTime();
        /*MyFiles.copyFileUsingStream(in, out);
        System.out.println("Time taken to copy file using Stream = " + (System.nanoTime() - start));

        start = System.nanoTime();
        MyFiles.copyFileUsinfBufferedStream(in, outBuffered);
        System.out.println("Time taken to copy file using Buffered Stream = " + (System.nanoTime() - start));

        start = System.nanoTime();
        MyFiles.copyFileUsingNio(in, outChannel);
        System.out.println("Time taken to copy file using NIO Channel = " + (System.nanoTime() - start));

        start = System.nanoTime();
        MyFiles.copyFileUsingJavaFiles(in, outFiles);
        System.out.println("Time taken to copy file using NIO Files = " + (System.nanoTime() - start));

        start = System.nanoTime();*/
        //MyFiles.copyFileFromURLUsingJavaFiles(myURLTxt, outPathTxt);
        MyFiles.copyFileFromURLUsingBufferedReader(myURLTxt, outPathTxt);
        System.out.println("Time taken to copy file from URL using Buffered Stream = " + (System.nanoTime() - start));

        /*start = System.nanoTime();
        MyFiles.copyFileFromURLUsingJavaFiles(myURLJpg, outPathJpg);
        System.out.println("Time taken to copy file from URL using NIO Files = " + (System.nanoTime() - start));
*/
    }
}
