package com.wxpay.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Chenzq on 2018/3/20.
 */
public class FileUtils {
    public static String loadStrFromFile(String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        StringBuffer buffer = new StringBuffer();
        BufferedReader bufr = null;
        try {
            bufr = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = bufr.readLine()) != null) {
                buffer.append(line);
            }
        } finally {
            if (bufr != null)
                bufr.close();
        }
        return buffer.toString();
    }
}