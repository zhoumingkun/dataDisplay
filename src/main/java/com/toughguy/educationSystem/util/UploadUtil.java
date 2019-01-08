package com.toughguy.educationSystem.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具类 UploadUtil
 * @author lanbin
 * @date 2017-3-9
 */
public class UploadUtil {

	/**
	 * 获取文件上传文件夹的绝对路径，用于上传文件（E:/xxx/upload/content）
	 * @param moduleName 模块名称（如：content、expert等）
	 * @param session http会话
	 * @return 路径String
	 */
	public static String getAbsolutePath(String moduleName) {
		return System.getProperty("user.dir") + "/upload/" + moduleName;
	}
	
	/**
	 * 获取文件上传相对于项目的路径，用于存储到数据库，和页面读取展示文件（upload/content）
	 * @param moduleName 模块名称（如：content、expert等）
	 * @param session http会话
	 * @return 路径String
	 */
	public static String getRelativePath(String moduleName) {
		return "upload/" + moduleName;
	}
	
	/**
	 * 判断文件名是否是图片类型的文件（jpg、jpeg、png、bmp、gif）
	 * @param fileName 文件名
	 * @return true-是/false-否
	 */
	public static boolean isPicture(String fileName) {
		String extName = FilenameUtils.getExtension(fileName);
		if ("jpg".equals(extName) || "jpeg".equals(extName) || "png".equals(extName) || "bmp".equals(extName) || "gif".equals(extName) || "JPEG".equals(extName) || "JPG".equals(extName) || "PNG".equals(extName) || "BMP".equals(extName) || "GIF".equals(extName)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断文件名是否是word或pdf类型的文件（doc、docx、pdf）
	 * @param fileName 文件名
	 * @return true-是/false-否
	 */
	public static boolean isAttachment(String fileName) {
		String extName = FilenameUtils.getExtension(fileName);
		if ("doc".equals(extName) || "docx".equals(extName) || "pdf".equals(extName)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 自定义方式重命名文件
	 * @param fileName 原文件名
	 * @return 新文件名（当前时间+4位随机数+扩展名：201703091011581104.png）
	 */
	public static String rename(String fileName) {
		// 获取扩展名
		String extName = FilenameUtils.getExtension(fileName);
		// 获取4位随机数字符串
		//String ranStr = RandomStringUtils.random(4, false, true);
		String ranStr = new RandomStringGenerator.Builder().withinRange('0', '9').build().generate(4);
		// 当前时间字符串
		String timeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); 
		return timeStr + ranStr + "." + extName;
	}
	
	public static String uploadPicture(MultipartFile pictureFile) throws Exception{
				// 重命名文件
				String newName = UploadUtil.rename(pictureFile.getOriginalFilename());
				// 获取存储路径
				String absolutePath = UploadUtil.getAbsolutePath("picture");
				String relativePath = UploadUtil.getRelativePath("picture");
				// 先上传文件（绝对路径）
				File f = new File(absolutePath);  //无路径则创建 
				if(!f.exists()){
					f.mkdirs();
				}
				File targetFile = new File(absolutePath + "/" + newName);
				pictureFile.transferTo(targetFile);
				return newName;
	}
}
