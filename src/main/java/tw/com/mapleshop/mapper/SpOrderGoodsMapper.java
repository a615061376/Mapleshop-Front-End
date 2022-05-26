package tw.com.mapleshop.mapper;

import org.springframework.stereotype.Repository;
import tw.com.mapleshop.entity.SpOrderGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 商品訂單關聯表 Mapper 接口
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Repository
public interface SpOrderGoodsMapper extends BaseMapper<SpOrderGoods> {

}
