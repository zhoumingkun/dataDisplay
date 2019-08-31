package com.toughguy.alarmSystem.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/* 
* 条码工具类
* @since JDK1.7
* @version 1.0
*/
public class BarCodeUtil {
	
//  关于Zxing 中条码格式	
//	BarcodeFormat.CODE_128; // 表示高密度数据， 字符串可变长，符号内含校验码  
//	BarcodeFormat.CODE_39;    
//	BarcodeFormat.CODABAR; // 可表示数字0 - 9，字符$、+、 -、还有只能用作起始/终止符的a,b,c d四个字符，可变长度，没有校验位  
//	BarcodeFormat.DATA_MATRIX; //二维码 
//	BarcodeFormat.EAN_8;  
//	BarcodeFormat.EAN_13;  
//	BarcodeFormat.PDF_417; // 二维码  
//	BarcodeFormat.QR_CODE; // 二维码  
//	BarcodeFormat.UPC_E; // 统一产品代码E:7位数字,最后一位为校验位  
//	BarcodeFormat.UPC_A; // 统一产品代码A:12位数字,最后一位为校验位  
//	BarcodeFormat.UPC_EAN_EXTENSION;  
	
	/*
	 * 新建二维码，条形码方法（zxing）
	 * int flag   1 为条形码  2为二维码
	 * int width  宽度
	 * int height 高度
	 * String text 二维码或条形码信息
	 * String path 存储路径
	 * 
	 * */
	public static void createEncode(int flag, int width,int height,String text,String path){
		 	String format = "png";	       
	        // 条形码的格式是 BarcodeFormat.EAN_13  BarcodeFormat.CODE_128
	        // 二维码的格式是BarcodeFormat.QR_CODE  
	        BitMatrix bm = null;
			try {
				 HashMap<EncodeHintType, String> hints = new HashMap<>();  
			      hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
			      if(flag == 1){
			    	  bm = new MultiFormatWriter().encode(text,  
							BarcodeFormat.CODE_128, width, height, hints);
			      } else if(flag ==2 ){
			    	  bm = new MultiFormatWriter().encode(text,  
							BarcodeFormat.QR_CODE, width, height, hints);
			      }else{
			    	  bm = null;
			      }
			      	File out = new File(path);  
			        WriteBitMatricToFile.writeBitMatricToFile(bm, format, out);  
			} catch (WriterException e) {
				e.printStackTrace();
		}  
	}
	/**
     * 生成文件  barcode4j
     *
     * @param msg
     * @param path
     * @return
     */
    public static File generateFile(String msg, String path) {
        File file = new File(path);
        try {
            generate(msg, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
 
    /**
     * 生成字节
     *
     * @param msg
     * @return
     */
    public static byte[] generate(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous);
        return ous.toByteArray();
    }
 
    /**
     * 生成到流    barcode4j
     * 可生成类型   Code39Bean  
     * Code 128
		EAN-13
		EAN-8
     * @param msg
     * @param ous
     */
    private static void generate(String msg, OutputStream ous) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return;
        } 
        //Code128Bean bean = new Code128Bean();
        Code39Bean bean = new Code39Bean();
        // 精细度
        final int dpi = 150;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
 
        // 配置对象
        bean.setModuleWidth(moduleWidth);
        //bean.setWideFactor(3);
        bean.doQuietZone(false);
 
        String format = "image/png";
        try {
 
            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);
 
            // 生成条形码
            bean.generateBarcode(canvas, msg);
            // 结束绘制
            canvas.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
	 * 自定义方式重命名文件
	 * @param fileName 原文件名
	 * @return 新文件名（当前时间+4位随机数+扩展名：201703091011581104.png）
	 */
	public static String rename(String file) {
		// 获取4位随机数字符串
		String ranStr = new RandomStringGenerator.Builder().withinRange('0', '9').build().generate(4);
		// 当前时间字符串
		String timeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); 
		return timeStr + ranStr + "."+ file;
	}
	
	/**
	 * 图片的旋转
	 *  */
	public static BufferedImage Rotate(Image src, int angel) {  
        int src_width = src.getWidth(null);  
        int src_height = src.getHeight(null);  
        // calculate the new image size  
        Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(  
                src_width, src_height)), angel);  
  
        BufferedImage res = null;  
        res = new BufferedImage(rect_des.width, rect_des.height,  
                BufferedImage.TYPE_INT_RGB);  
        Graphics2D g2 = res.createGraphics();  
        // transform  
        g2.translate((rect_des.width - src_width) / 2,  
                (rect_des.height - src_height) / 2);  
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);  
  
        g2.drawImage(src, null, null);  
        return res;  
    }  
  
    public static Rectangle CalcRotatedSize(Rectangle src, int angel) {  
        // if angel is greater than 90 degree, we need to do some conversion  
        if (angel >= 90) {  
            if(angel / 90 % 2 == 1){  
                int temp = src.height;  
                src.height = src.width;  
                src.width = temp;  
            }  
            angel = angel % 90;  
        }  
  
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;  
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;  
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;  
        double angel_dalta_width = Math.atan((double) src.height / src.width);  
        double angel_dalta_height = Math.atan((double) src.width / src.height);  
  
        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha  
                - angel_dalta_width));  
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha  
                - angel_dalta_height));  
        int des_width = src.width + len_dalta_width * 2;  
        int des_height = src.height + len_dalta_height * 2;  
        return new java.awt.Rectangle(new Dimension(des_width, des_height));  
    } 
}
