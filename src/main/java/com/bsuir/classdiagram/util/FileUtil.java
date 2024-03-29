package com.bsuir.classdiagram.util;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.bsuir.classdiagram.util.Constant.UML_TEXT_PATH;

public class FileUtil {

    public static List<String> findFilesPathBy(File dir) throws FileNotFoundException {
        if (!dir.exists()) {
            throw new FileNotFoundException(dir.toString());
        }
        List<String> files = new ArrayList<>();
        get(dir, files);
        return files;
    }

    private static void get(File file, List files) {
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (f.isDirectory()) {
                get(f, files);
            }
            if (f.isFile()) {
                files.add(f.getPath());
            }
        }
    }

    public static void write(String res) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            File distFile = new File(UML_TEXT_PATH);
            if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs();
            bufferedReader = new BufferedReader(new StringReader(res));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            char buf[] = new char[1024];
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
