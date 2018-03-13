package com.appbaselib.utils;

import android.net.Uri;
import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author hht
 * @Description: TODO
 * @date 2017/2/9 0009
 */
public class FileUtlis {
    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        if (files == null) {
            return 0;
        }
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 转换文件大小
     *
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(File dir) {
        long fileS = getDirSize(dir);
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static boolean deleFile(File dir) {
        if (dir == null) {
            return true;
        }
        if (!dir.isDirectory()) {
            return true;
        }

        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                deleFile(file);
            }
        }
        return true;
    }

    /**
     * 文件转base64字符串
     *
     * @return
     */
    public static String fileToBase64(File mFile) {
        FileInputStream inputFile = null;
        try {
            inputFile = new FileInputStream(mFile);
        } catch (FileNotFoundException mE) {
            mE.printStackTrace();
        }
        byte[] buffer = new byte[(int) mFile.length()];
        try {
            inputFile.read(buffer);
        } catch (IOException mE) {
            mE.printStackTrace();
        }
        try {
            inputFile.close();
        } catch (IOException mE) {
            mE.printStackTrace();
        }
        return "data:image/" + getSuffix(mFile) + ";base64," + Base64.encodeToString(buffer, Base64.DEFAULT);
    }

    public static String fileToBase64(Uri mUri) {
        File mFile= null;
        try {
            mFile = new File(new URI(mUri.toString()));
        } catch (URISyntaxException mE) {
            mE.printStackTrace();
        }

        FileInputStream inputFile = null;
        try {
            inputFile = new FileInputStream(mFile);
        } catch (FileNotFoundException mE) {
            mE.printStackTrace();
        }
        byte[] buffer = new byte[(int) mFile.length()];
        try {
            inputFile.read(buffer);
        } catch (IOException mE) {
            mE.printStackTrace();
        }
        try {
            inputFile.close();
        } catch (IOException mE) {
            mE.printStackTrace();
        }
        return "data:image/" + getSuffix(mFile) + ";base64," + Base64.encodeToString(buffer, Base64.DEFAULT);
    }

    public static String getSuffix(File mFile) {
        String fileName = mFile.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


}
