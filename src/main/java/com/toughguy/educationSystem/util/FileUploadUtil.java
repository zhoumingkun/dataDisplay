package com.toughguy.educationSystem.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 上传文件工具类
 * @author BOBO
 *
 */
public class FileUploadUtil {
	private static final int numOfEncAndDec = 0x99;//加密解密秘钥
	private static int dataOfFile = 0;  //文件字节内容

	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
	    File targetFile = new File(filePath); 
	    if(!targetFile.exists()){  
	      targetFile.mkdirs();  
	    }    
	    FileOutputStream out = new FileOutputStream(filePath+fileName);
	    out.write(file);
	    out.flush();
	    out.close();
	  }

	/**
	 * 加密
	 * @param srcFile
	 * @param encFile
	 * @throws Exception
	 */
//	private static void EncFile(File srcFile, File encFile) throws Exception {
//		if(!srcFile.exists()) {
//			System.out.println("source file not exixt");
//			return;
//		}
//		if(!encFile.exists()) {
//			System.out.println("encrypt file created");
//			encFile.createNewFile();
//		}
//		InputStream fis = new FileInputStream(srcFile);
//		OutputStream fos = new FileOutputStream(encFile);
//		
//		while((dataOfFile = fis.read()) > -1) {
//			fos.write(dataOfFile^numOfEncAndDec);
//		}
//		
//		fis.close();
//		fos.flush();
//		fos.close();
//	}
	/**
	 * 解密
	 * @param encFile
	 * @param decFile
	 * @throws Exception
	 */
//	private static void DecFile(File encFile, File decFile) throws Exception {
//		if(!encFile.exists()){
//			System.out.println("encrypt file not exixt");
//				return;
//		}
//		if(!decFile.exists()){
//			System.out.println("decrypt file created");
//			decFile.createNewFile();
//		}
//		 
//		InputStream fis  = new FileInputStream(encFile);
//		OutputStream fos = new FileOutputStream(decFile);
//		 
//		while ((dataOfFile = fis.read()) > -1) {
//			fos.write(dataOfFile^numOfEncAndDec);
//		}
//		
//		fis.close();
//		fos.flush();
//		fos.close();
//	}
}
