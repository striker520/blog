package com.fkzm.blog.apputils;

import sun.security.rsa.RSASignature;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5  {
    public static String encrypt(String str){
        try{
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] bytesDigest=md.digest();

            StringBuilder sb=new StringBuilder();

            for(int offset=0,i=0;offset<bytesDigest.length;offset++){
                i=bytesDigest[offset];
                if(i<0)i+=256;
                if(i<16)sb.append("0");
                sb.append(Integer.toHexString(i));
            }
            //32位加密
            return sb.toString();
//            16位加密
//            return sb.substring(8,24).toString();


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        System.out.println(MD5.encrypt("1323"));
    }

}
