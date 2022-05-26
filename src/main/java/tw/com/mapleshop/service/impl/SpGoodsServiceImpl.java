package tw.com.mapleshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import tw.com.mapleshop.entity.SpGoods;
import tw.com.mapleshop.mapper.SpGoodsMapper;
import tw.com.mapleshop.service.SpGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Service
public class SpGoodsServiceImpl extends ServiceImpl<SpGoodsMapper, SpGoods> implements SpGoodsService {

    @Autowired
    private SpGoodsMapper spGoodsMapper;

    @Override
    public List<SpGoods> findAllByLevelAndSpCategoryId(Integer catLevel, Integer catId) {
        QueryWrapper<SpGoods> queryWrapper = new QueryWrapper<>();
        String column = null;
        switch (catLevel) {
            case 0:
                column = "cat_one_id";
                break;
            case 1:
                column = "cat_two_id";
                break;
            case 2:
                column = "cat_three_id";
                break;
        }
        queryWrapper.eq(column, catId);
        return this.spGoodsMapper.selectList(queryWrapper);
    }
}
