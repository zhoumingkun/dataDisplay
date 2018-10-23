package com.toughguy.reportingSystem.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.common.BitMatrix;

public class WriteBitMatricToFile {
	private static final int BLACK = 0xFF000000;  
    private static final int WHITE = 0xFFFFFFFF;  
  
    private static BufferedImage toBufferedImage(BitMatrix bm) {  
        int width = bm.getWidth();  
        int height = bm.getHeight();  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_3BYTE_BGR);  
        for (int i = 0; i < width; i++) {  
            for (int j = 0; j < height; j++) {  
                image.setRGB(i, j, bm.get(i, j) ? BLACK : WHITE);  
            }  
        }  
        return image;  
    }  
  
    public static void writeBitMatricToFile(BitMatrix bm, String format,  
            File file) {  
        BufferedImage image = toBufferedImage(bm);  
        try {  
            if (!ImageIO.write(image, format, file)) {  
                throw new RuntimeException("Can not write an image to file" + file);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static void writeToStream(BitMatrix matrix, String format,  
            OutputStream stream) throws IOException {  
        BufferedImage image = toBufferedImage(matrix);  
        if (!ImageIO.write(image, format, stream)) {  
            throw new IOException("Could not write an image of format " + format);  
        }  
    }  
}
