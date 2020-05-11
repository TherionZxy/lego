package com.zxyono.lego.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 图片上传工具类
 */
@Component
public class ImageUpload {
    @Value("${lego.images.path}")
    private String uploadPath;

    /**
     * 保存图片，并且返回保存后的图片路径，支持保存原图、缩略图等
     * @param file
     * @return
     */
    public String saveImage(MultipartFile file, ImageSize... sizes) {
        // 获取原始图片名
        String originName = file.getOriginalFilename();
        // 获取图片扩展名
        String[] originSplit = originName.split("\\.");
        String extendName = originSplit[originSplit.length - 1];
        // 获取用于保存的随机图片名
        String filepath = absFilePath(originName);
        // 创建图片文件夹
        File imagesDir = new File(filepath);
        if (!imagesDir.exists()) {
            imagesDir.mkdir();
        }

        String origin = filepath + "/origin." + extendName;
        // 保存图片
        try {
            // 保存原图
            file.transferTo(new File(origin));
            // 保存略缩图
            for (ImageSize size : sizes) {
                Thumbnails.of(origin).size(size.getWidth(), size.getHeight()).toFile(filepath + '/' +
                        size.getWidth() + 'x' + size.getHeight() + '.' + extendName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return origin;
    }

    /**
     * 生成随机的图片名称
     * @param originName
     * @return filename
     */
    public String randomFileName(String originName) {
        return  String.valueOf(new Date().getTime()) + Math.abs(new Random().nextInt(10000) + 10000);
    }

    /**
     * 获取完整的文件路径
     * @param originName
     * @return filepath
     */
    public String absFilePath(String originName) {
        return uploadPath + randomFileName(originName);
    }

}