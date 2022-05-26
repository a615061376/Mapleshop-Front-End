package tw.com.mapleshop.mapper;

import org.springframework.stereotype.Repository;
import tw.com.mapleshop.entity.SpGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Repository
public interface SpGoodsMapper extends BaseMapper<SpGoods> {
    public Integer updateNumberById(Integer goodsId, Integer goodsNumber);
    public Integer getNumberById(Integer goodsId);
}
