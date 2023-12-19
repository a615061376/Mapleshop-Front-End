package tw.com.mapleshop.service;

import tw.com.mapleshop.entity.SpGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SpGoodsService extends IService<SpGoods> {

    public List<SpGoods> findAllByLevelAndSpCategoryId(Integer catLevel, Integer catId);
}
