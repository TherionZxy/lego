package com.zxyono.lego.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 可以设置图片参数来规定保存后的图片的样式
 */
@Data
@AllArgsConstructor
public class ImageSize {
    private int width;
    private int height;
}