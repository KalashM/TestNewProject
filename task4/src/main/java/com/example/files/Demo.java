package com.example.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Demo {

    private static Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws IOException {

        /*File[] matchedFiles = MyFiles.findFilesByPattern("D:\\", "(.*)fo(.*)");
        for (File matchedFile : matchedFiles) {
            System.out.println(matchedFile.getName());
        }*/

        File in = new File("task4\\src\\test\\resources\\Bruce Eckel - Thinking in Java - 2015.pdf");
        File out = new File("task4\\src\\test\\resources\\COPY_Bruce Eckel - Thinking in Java - 2015.pdf");
        File outBuffered = new File("task4\\src\\test\\resources\\COPYBuffered_Bruce Eckel - Thinking in Java - 2015.pdf");
        File outChannel = new File("task4\\src\\test\\resources\\COPYChannel_BBruce Eckel - Thinking in Java - 2015.pdf");
        File outFiles = new File("task4\\src\\test\\resources\\COPYFiles_Bruce Eckel - Thinking in Java - 2015.pdf");

        String myURLTxt = "https://raw.githubusercontent.com/KalashM/TestNewProject/436416cee51e4a5f614ebe0004017fdd80814356/task4/src/main/java/com/example/files/MyFiles.java";
        String outPathTxt = "task4\\src\\test\\resources";

        LOGGER.info("File size total = " + (in.length() / (1024 * 1024)) + " mb");

        long start = System.nanoTime();
        MyFiles.copyFileUsingStream(in, out);
        LOGGER.info("Time taken to copy file using Stream = " + (System.nanoTime() - start));

        start = System.nanoTime();
        MyFiles.copyFileUsingBufferedStream(in, outBuffered);
        LOGGER.info("Time taken to copy file using Buffered Stream = " + (System.nanoTime() - start));

        start = System.nanoTime();
        MyFiles.copyFileUsingNioChannel(in, outChannel);
        LOGGER.info("Time taken to copy file using NIO Channel = " + (System.nanoTime() - start));

        start = System.nanoTime();
        MyFiles.copyFileUsingJavaFiles(in, outFiles);
        LOGGER.info("Time taken to copy file using Java Files = " + (System.nanoTime() - start));

        start = System.nanoTime();
        MyFiles.copyFileFromURLUsingBufferedReader(myURLTxt, outPathTxt);
        LOGGER.info("Time taken to copy file from URL using Buffered Reader = " + (System.nanoTime() - start));

        start = System.nanoTime();
        MyFiles.copyFileFromURLUsingBufferedStream(myURLTxt, outPathTxt);
        LOGGER.info("Time taken to copy file from URL using Buffered Stream = " + (System.nanoTime() - start));

        start = System.nanoTime();
        MyFiles.copyFileFromURLUsingJavaFiles(myURLTxt, outPathTxt);
        LOGGER.info("Time taken to copy file from URL using Java Files = " + (System.nanoTime() - start));
    }
}
