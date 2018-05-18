package com.appbaselib.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tangming on 2018/5/15.
 */

public class Md5AndSha1CaculateUtils {


    public static String md5(String data) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException mE) {
            mE.printStackTrace();
        }
        md.update(data.getBytes());
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();
        for(int i=0;i<bits.length;i++){
            int a = bits[i];
            if(a<0) a+=256;
            if(a<16) buf.append("0");
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }

    public static String sha1(String data)  {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException mE) {
            mE.printStackTrace();
        }
        md.update(data.getBytes());
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();
        for(int i=0;i<bits.length;i++){
            int a = bits[i];
            if(a<0) a+=256;
            if(a<16) buf.append("0");
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }
}
