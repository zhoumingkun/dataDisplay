package com.toughguy.dataDisplay.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Transformation {
	
	/**
	  * base64编码字符串转换为图片
	  * @param imgStr base64编码字符串
	  * @param path 图片路径
	  * @return
	  */
	
	 public static boolean base64StrToImage(String imgStr, String path) {
		 if (imgStr == null) //图像数据为空 
		      return false; 
		    BASE64Decoder decoder = new BASE64Decoder(); 
		    try 
		    { 
		      //Base64解码 
		      byte[] b = decoder.decodeBuffer(imgStr); 
		      for(int i=0;i<b.length;++i) 
		      { 
		        if(b[i]<0) 
		        {//调整异常数据 
		          b[i]+=256; 
		        } 
		      } 
		      //生成图片 
		      OutputStream out = new FileOutputStream(path);   
		      out.write(b); 
		      out.flush(); 
		      out.close(); 
		      return true; 
		    }  
		    catch (Exception e)  
		    { 
		    	e.printStackTrace();
		    	return false; 
		    } 
  } 
	 /**
	  * 图片转base64字符串
	  * @param imgFile 图片路径
	  * @return
	  */
	 public static String imageToBase64Str(String imgFile) {
	   InputStream inputStream = null;
	   byte[] data = null;
	   try {
	     inputStream = new FileInputStream(imgFile);
	     data = new byte[inputStream.available()];
	     inputStream.read(data);
	     inputStream.close();
	   } catch (IOException e) {
	     e.printStackTrace();
	   }
	   // 加密
	   BASE64Encoder encoder = new BASE64Encoder();
	   return encoder.encode(data);
	 }

}
