package tw.com.mapleshop.service.impl;

import tw.com.mapleshop.entity.SpOrderGoods;
import tw.com.mapleshop.mapper.SpOrderGoodsMapper;
import tw.com.mapleshop.service.SpOrderGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品訂單關聯表 服务实现类
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Service
public class SpOrderGoodsServiceImpl extends ServiceImpl<SpOrderGoodsMapper, SpOrderGoods> implements SpOrderGoodsService {

}
