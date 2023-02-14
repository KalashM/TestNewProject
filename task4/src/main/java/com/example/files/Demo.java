package com.example.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.*;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

public class Demo {

    private static Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws IOException {

        LOGGER.info("Look up files in a directory by pattern:");
        Path dir = Paths.get("task4\\src\\main\\java\\com\\example\\files");
        LOGGER.info(dir.toString());
        File[] matchedFiles = MyFiles.findFilesByPattern(dir, "(.*)Demo(.*)");
         for (File matchedFile : matchedFiles) {
            LOGGER.info(matchedFile.getName());
        }
         //Create dummy file:
        if (Files.isDirectory(Paths.get(args[0]))) {
            createDummyHugeFile(args[0]);
        }

        File in = new File(args[0] + "\\hugefile.txt");
        File out = new File(args[0] + "\\COPYStream_hugefile.txt");
        File outBuffered = new File(args[0] + "\\COPYBuffered_hugefile.txt");
        File outChannel = new File(args[0] + "\\COPYChannel_hugefile.txt");
        File outFiles = new File(args[0] + "\\COPYFiles_hugefile.txt");

        String myURLTxt = "https://raw.githubusercontent.com/KalashM/TestNewProject/436416cee51e4a5f614ebe0004017fdd80814356/task4/src/main/java/com/example/files/MyFiles.java";
        String outPathTxt = args[0];

        LOGGER.info("File size = " + (in.length() / (1024 * 1024)) + " mb");

        long start, end, elapsedTime, convert;
        start = System.nanoTime();
        MyFiles.copyFileUsingStream(in, out);
        end = System.nanoTime();
        elapsedTime = end - start;
        convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        LOGGER.info("Time taken to copy file using Stream = " + convert + " seconds");

        start = System.nanoTime();
        MyFiles.copyFileUsingBufferedStream(in, outBuffered);
        end = System.nanoTime();
        elapsedTime = end - start;
        convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        LOGGER.info("Time taken to copy file using Buffered Stream = " + convert + " seconds");

        start = System.nanoTime();
        MyFiles.copyFileUsingNioChannel(in, outChannel);
        end = System.nanoTime();
        elapsedTime = end - start;
        convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        LOGGER.info("Time taken to copy file using NIO Channel = " + convert + " seconds");

        start = System.nanoTime();
        MyFiles.copyFileUsingJavaFiles(in, outFiles);
        end = System.nanoTime();
        elapsedTime = end - start;
        convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        LOGGER.info("Time taken to copy file using Java Files = " + convert + " seconds");

        start = System.nanoTime();
        MyFiles.copyFileFromURLUsingBufferedReader(myURLTxt, outPathTxt);
        end = System.nanoTime();
        elapsedTime = end - start;
        convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        LOGGER.info("Time taken to copy file from URL using Buffered Reader = " + convert + " seconds");

        start = System.nanoTime();
        MyFiles.copyFileFromURLUsingBufferedStream(myURLTxt, outPathTxt);
        end = System.nanoTime();
        elapsedTime = end - start;
        convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        LOGGER.info("Time taken to copy file from URL using Buffered Stream = " + convert + " seconds");

        start = System.nanoTime();
        MyFiles.copyFileFromURLUsingJavaFiles(myURLTxt, outPathTxt);
        end = System.nanoTime();
        elapsedTime = end - start;
        convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        LOGGER.info("Time taken to copy file from URL using Java Files = " + convert + " seconds");

        LOGGER.info("Test reading a Matrix from a file:");
        Integer[][] myMatrix = MyFiles.readMatrixFromFile(new File("task4\\src\\test\\resources\\matrix.txt"));
        LOGGER.info("Find average value of all matrix elements:");
        int sum = 0;
        int count = 0;
        for (int i = 0; i < myMatrix.length; i++) {
            for (int j = 0; j < myMatrix[i].length; j++) {
                count++;
                sum = sum + myMatrix[i][j];
            }
        }
        float avg = sum / count;
        LOGGER.info("Average value = " + avg);
    }

    public static void createDummyHugeFile(String path) {
        final ByteBuffer buf = ByteBuffer.allocate(4).putInt(2);
        buf.rewind();

        final OpenOption[] options = {StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW , StandardOpenOption.SPARSE};
        final Path hugeFile = Paths.get(path + "\\hugefile.txt");
        try (final SeekableByteChannel channel = Files.newByteChannel(hugeFile, options);) {
            channel.position(1024 * 1024 * 1024);
            channel.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
