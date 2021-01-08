package com.sgxy.o2o.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Base64;
import java.util.Base64.Decoder;


@SuppressWarnings("finally")
public class Base64Utils {

  public static boolean GenerateImage(String imgData, String imgFilePath) throws IOException { // ���ֽ������ַ�������Base64���벢����ͼƬ
	         if (imgData == null) // ͼ������Ϊ��
	             return false;
	         Decoder decoder=Base64.getMimeDecoder();
	         OutputStream out = null;
	        try {
	           out = new FileOutputStream(imgFilePath);
				byte[] b = decoder.decode(imgData);
	           for (int i = 0; i < b.length; ++i) {
	               if (b[i] < 0) {// �����쳣����
	                   b[i] += 256;
	               }
	           }
	           out.write(b);
	       } catch (FileNotFoundException e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	       } catch (IOException e) {
	          // TODO Auto-generated catch block
	           e.printStackTrace();
	       } finally {
	             out.flush();
	             out.close();
	             return true;
	         }
	     }
}