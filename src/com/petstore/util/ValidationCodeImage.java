package com.petstore.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by hezhujun on 2016/4/8.
 * 生成验证码图片
 */
public class ValidationCodeImage {

    public static BufferedImage createImages(String validationCode) {
        // 验证码长度
        int codeLength = validationCode.length();
        // 字体大小
        int fontSize = 15;
        int fontWidth = fontSize + 1;
        // 图片宽度
        int width = codeLength * fontWidth + 6;
        // 图片高度
        int height = fontSize * 2 + 1;
        // 图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        // 设置背景色
        g.setColor(Color.white);
        // 填充背景色
        g.fillRect(0, 0, width, height);
        // 设置边框颜色
        g.setColor(Color.LIGHT_GRAY);
        // 边框字体样式
        g.setFont(new Font("Arial", Font.BOLD, height - 2));
        // 绘制边框
        g.drawRect(0, 0, width - 1, height - 1);
        // 绘制噪点
        Random random = new Random();
        // 设置噪点颜色
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < codeLength * 6; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            // 绘制1*1大小的矩形
            g.drawRect(x, y, 1, 1);
        }
        // 绘制验证码
        int codeY = height - 10;
        // 设置字体颜色
        g.setColor(new Color(19, 148, 246));
        g.setFont(new Font("Georgia", Font.BOLD, fontSize));
        for (int i = 0; i < codeLength; i++) {
            g.drawString(String.valueOf(validationCode.charAt(i)), i * 16 + 5, codeY);
        }
        // 关闭资源
        g.dispose();
        return image;
    }

    /**
     * 返回验证码图片的流格式
     * @param validationCode 验证码
     * @return ByteArrayInputStream 图片流
     */
    public static ByteArrayInputStream getImageAsInputStream(String validationCode){
        BufferedImage image = createImages(validationCode);
        return convertImageToStream(image);
    }

    /**
     * 将BufferedImage转换成ByteArrayInputStream
     * @param image 图片
     * @return ByteArrayInputStream 流
     */
    public static ByteArrayInputStream convertImageToStream(BufferedImage image){
        ByteArrayInputStream inputStream = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(bos);
        try{
            jpeg.encode(image);
            byte[] bts = bos.toByteArray();
            inputStream = new ByteArrayInputStream(bts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

}
