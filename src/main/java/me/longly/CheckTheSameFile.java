package me.longly;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class CheckTheSameFile {


    public static void main(String[] args) throws IOException {


        Map<String, File> allFiles = new HashMap<String, File>();

        String path = "F:/picture/";
        File filePath = new File(path);

        List<File> files = new ArrayList<File>();

        allFiles(filePath, files);

        Map<String, HashSet<String>> mapFiles = new HashMap<String, HashSet<String>>();

        for (File f : files) {
            FileInputStream fis = new FileInputStream(f);
            String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
            IOUtils.closeQuietly(fis);

            if (mapFiles.containsKey(md5)) {

                HashSet<String> set = mapFiles.get(md5);
                set.add(f.getAbsolutePath());
            } else {
                HashSet<String> set = new HashSet<String>();
                set.add(f.getAbsolutePath());

                mapFiles.put(md5, set);
            }
        }


        for (String key : mapFiles.keySet()) {
            HashSet<String> hashSet = mapFiles.get(key);
            if (hashSet.size()>1) {
                System.out.println();
                System.out.println(hashSet.toString());
                System.out.println();
            }
        }


    }

    public static void allFiles(File file, List<File> files) {

        if (!file.isDirectory()) {
            files.add(file);
        } else {
            for (File f : file.listFiles()) {
                allFiles(f, files);
            }
        }
    }


}

