package com.zxyono.lego.entity.wrapper;

import lombok.Data;

@Data
public class FruitWrapper {
    private String fruitName;
    private Integer isSale;
    private Integer isFlashSale;

    public boolean isEmpty() {
        if(fruitName == null && isSale == null && isFlashSale == null) {
            return true;
        } else {
            return false;
        }
    }
}