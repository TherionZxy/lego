package com.zxyono.lego.entity.vo;

import com.zxyono.lego.entity.Fruit;
import lombok.Data;

import java.util.List;

@Data
public class FruitVo {
    private Integer page;
    private Integer size;
    private Long total;
    private List<Fruit> fruitList;
}