package com.zxyono.lego.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxyono.lego.entity.Fruit;
import com.zxyono.lego.entity.vo.FruitVo;
import com.zxyono.lego.entity.wrapper.FruitWrapper;
import com.zxyono.lego.mapper.FruitMapper;
import com.zxyono.lego.service.FruitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FruitServiceImpl implements FruitService {
    @Resource
    FruitMapper fruitMapper;

    /**
     * 封装创建QueryWrapper方法
     * @param params
     * @return
     */
    public QueryWrapper<Fruit> createQueryWrapper(FruitWrapper params) {
        QueryWrapper<Fruit> queryWrapper = null;
        if (!params.isEmpty()) {
            // 当有查询条件时再实例化QueryWrapper
            queryWrapper = new QueryWrapper<>();
            if (params.getFruitName() != null && !"".equals(params.getFruitName())) {
                queryWrapper.like("fruit_name", params.getFruitName());
            }
            if (params.getIsSale() != null) {
                queryWrapper.eq("is_sale", params.getIsSale());
            }
            if (params.getIsFlashSale() != null) {
                queryWrapper.eq("is_flash_sale", params.getIsFlashSale());
            }
        }
        return queryWrapper;
    }

    @Override
    public FruitVo queryFruitList(Integer page, Integer size, FruitWrapper params) {
        FruitVo fruitVo = new FruitVo();
        IPage<Fruit> iPage = new Page<Fruit>(page, size);

        QueryWrapper<Fruit> queryWrapper = createQueryWrapper(params);

        fruitMapper.selectPage(iPage, queryWrapper);
        fruitVo.setPage(page);
        fruitVo.setSize(size);
        fruitVo.setTotal(iPage.getTotal());
        fruitVo.setFruitList(iPage.getRecords());
        return fruitVo;
    }

    @Override
    public List<Fruit> queryAllFruitList(FruitWrapper params) {
        QueryWrapper<Fruit> queryWrapper = createQueryWrapper(params);
        return fruitMapper.selectList(queryWrapper);
    }

    @Override
    public Fruit queryFruitById(Long fruitId) {
        return fruitMapper.selectOne(new QueryWrapper<Fruit>().eq("fruit_id", fruitId));
    }

    @Override
    public Integer createFruit(Fruit fruit) {
        // 抹去fruit的id，id由数据库自动生成，防止恶意攻击
        fruit.setFruitId(null);
        return fruitMapper.insert(fruit);
    }

    @Override
    public Integer modifyFruitInfo(Fruit fruit) {
        // 创建UpdateWrapper
        UpdateWrapper<Fruit> fruitUpdateWrapper = new UpdateWrapper<>();
        fruitUpdateWrapper
                .set(fruit.getFruitName() != null, "fruit_name", fruit.getFruitName())
                .set(fruit.getFruitIntro() != null, "fruit_intro", fruit.getFruitIntro())
                .set(fruit.getNormPrice() != null, "norm_price", fruit.getNormPrice())
                .set(fruit.getFruitPic() != null, "fruit_pic", fruit.getFruitPic())
                .set(fruit.getIsSale() != null && (fruit.getIsSale() == 0 || fruit.getIsSale() == 1), "is_sale", fruit.getIsSale())
                .set(fruit.getIsFlashSale() != null && (fruit.getIsFlashSale() == 0 || fruit.getIsFlashSale() == 1), "is_flash_sale", fruit.getIsFlashSale())
                .set(fruit.getDiscountPrice() != null, "discount_price", fruit.getDiscountPrice())
                .set(fruit.getMaxNum() != null, "max_num", fruit.getMaxNum())
                .set(fruit.getStartTime() != null, "start_time", fruit.getStartTime())
                .set(fruit.getEndTime() != null, "end_time", fruit.getEndTime())
                .eq("fruit_id", fruit.getFruitId());
        return fruitMapper.update(null, fruitUpdateWrapper);
    }

    @Override
    public Integer deleteFruitById(Long fruitId) {
        return fruitMapper.deleteById(fruitId);
    }
}