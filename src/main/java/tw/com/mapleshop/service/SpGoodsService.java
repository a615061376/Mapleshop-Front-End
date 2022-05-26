package tw.com.mapleshop.service;

import tw.com.mapleshop.entity.SpGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
public interface SpGoodsService extends IService<SpGoods> {

    public List<SpGoods> findAllByLevelAndSpCategoryId(Integer catLevel, Integer catId);
}
