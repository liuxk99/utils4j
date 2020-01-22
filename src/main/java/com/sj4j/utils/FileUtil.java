package com.sj4j.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * Utilities for File Operations.
 * Created by thomas(liuxk99@gmail.com) on 2015/11/10.
 */
public class FileUtil {
    private static final String TAG = FileUtil.class.getSimpleName();

    private static void writeFile(String fileName, int mode) {
        JLog.d(FileUtil.TAG, "writeFile(" + fileName + ", " + mode + ")");
        File targetFile = new File(fileName);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile),
                    32);
            try {
                bw.write(Integer.toString(mode));
            } finally {
                bw.close();
            }
        } catch (IOException e) {
            JLog.e(FileUtil.TAG, "IOException when write " + targetFile);
        }
    }

    public static String readStringFromFile(String fileName) {
        String content = null;

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return content;
        }

        br = new BufferedReader(fr);

        try {
            content = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return content;
        }

        try {
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static void writeStringToFile(String pathname, String msg) {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(pathname);
        } catch (IOException e) {
            e.printStackTrace();
        }

        bw = new BufferedWriter(fw);

        try {
            bw.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用文件通道的方式复制文件
     *
     * @param src 源文件
     * @param dst 复制到的新文件
     */

    static public void copyByChannel(File src, File dst) {
        FileInputStream fis;
        FileOutputStream fos;
        FileChannel inChannel;
        FileChannel outChannel;

        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dst);
            inChannel = fis.getChannel(); //得到对应的文件通道
            outChannel = fos.getChannel(); //得到对应的文件通道

            inChannel.transferTo(0, inChannel.size(), outChannel); //连接两个通道，并且从in通道读取，然后写入out通道

            fis.close();
            inChannel.close();
            fos.close();
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(String fileName, BufferedReader br) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            String line;
            do {
                line = br.readLine();
                if (line != null) {
                    bw.write(line);
                    bw.newLine();
                }
            } while (line != null);

            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(String fileName, List<String> lines) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }

            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(BufferedReader br) throws IOException {
        StringBuilder res = new StringBuilder();
        String line;
        do {
            line = br.readLine();
            if (line != null) {
                res.append(line).append("\n");
            }
        } while (line != null);
        return res.toString();
    }
}
