package com.zxyono.lego.service;

import com.zxyono.lego.entity.Fruit;
import com.zxyono.lego.entity.vo.FruitVo;
import com.zxyono.lego.entity.wrapper.FruitWrapper;

import java.util.List;

public interface FruitService {
    /**
     * 查询水果列表（分页、带查询参数）[用于需要分页的列表查询]
     * @param page
     * @param size
     * @param params
     * @return
     */
    public FruitVo queryFruitList(Integer page, Integer size, FruitWrapper params);

    /**
     * 查询水果列表（不分页、带查询参数）[用于Excel导出，因为导出为Excel时不需要进行分页]
     * @param params
     * @return
     */
    public List<Fruit> queryAllFruitList(FruitWrapper params);

    /**
     * 根据Id查询单个水果信息
     * @param fruitId
     * @return
     */
    public Fruit queryFruitById(Long fruitId);

    /**
     * 新增水果
     * @param fruit
     * @return
     */
    public Integer createFruit(Fruit fruit);

    /**
     * 修改水果信息（包含状态改变）
     * @param fruit
     * @return
     */
    public Integer modifyFruitInfo(Fruit fruit);

    /**
     * 根据Id删除水果信息
     * @param fruitId
     * @return
     */
    public Integer deleteFruitById(Long fruitId);

}
