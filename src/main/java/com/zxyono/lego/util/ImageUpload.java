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
    // 保存时的图片设置（在application.yml中进行配置）
    @Value("${lego.images.path}")
    private String uploadPath;
    @Value("#{'${lego.images.names}'.split(',')}")
    private String[] names;
    @Value("${lego.images.height}")
    private int height;
    @Value("${lego.images.width}")
    private int width;

    /**
     * 批量保存图片
     * @param filesList
     * @return
     */
    public String saveImages(List<MultipartFile>... filesList) {
        // 获取用于保存的随机图片名
        String randomFileName = randomFileName();
        String filepath = uploadPath + randomFileName;
        // 创建图片文件夹
        File imagesDir = new File(filepath);
        if (!imagesDir.exists()) {
            imagesDir.mkdir();
        }

        String extendStr = "";
        // 批量处理所有的图片
        try {
            for(int i=0;i<filesList.length;i++) {
                int j=1;
                for(;j<=filesList[i].size();j++) {
                    Thumbnails.of(filesList[i].get(j-1).getInputStream()).size(width, height).toFile(filepath + "/"
                            + names[i] + j + ".jpg");
                }
                extendStr = extendStr + "_" + (j-1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return randomFileName + extendStr;
    }

    /**
     * 生成随机的图片名称
     * @return filename
     */
    public String randomFileName() {
        return  String.valueOf(new Date().getTime()) + Math.abs(new Random().nextInt(10000) + 10000);
    }

    /**
     * 获取完整的文件路径
     * @return filepath
     */
    public String absFilePath() {
        return uploadPath + randomFileName();
    }

}