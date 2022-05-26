package tw.com.mapleshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tw.com.mapleshop.entity.SpGoods;
import tw.com.mapleshop.entity.SpOrder;
import tw.com.mapleshop.entity.SpOrderGoods;
import tw.com.mapleshop.mapper.SpGoodsMapper;
import tw.com.mapleshop.mapper.SpOrderGoodsMapper;
import tw.com.mapleshop.mapper.SpOrderMapper;
import tw.com.mapleshop.service.SpOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tw.com.mapleshop.vo.SpOrderGoodsVO;
import tw.com.mapleshop.vo.SpOrderVO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 訂單表 服务实现类
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Service
public class SpOrderServiceImpl extends ServiceImpl<SpOrderMapper, SpOrder> implements SpOrderService {

    @Autowired
    private SpOrderMapper spOrderMapper;

    @Autowired
    private SpOrderGoodsMapper spOrderGoodsMapper;

    @Autowired
    private SpGoodsMapper spGoodsMapper;

    @Override
    public List<SpOrderVO> findAllByUser(Integer userId) {
        QueryWrapper<SpOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<SpOrder> spOrderList = this.spOrderMapper.selectList(queryWrapper);
        List<SpOrderVO> spOrderVOList = new ArrayList<>();
        for (SpOrder spOrder : spOrderList) {
            SpOrderVO spOrderVO = new SpOrderVO();
            BeanUtils.copyProperties(spOrder, spOrderVO);
            QueryWrapper<SpOrderGoods> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("order_id", spOrder.getOrderId());
            List<SpOrderGoods> spOrderGoodsList = this.spOrderGoodsMapper.selectList(queryWrapper1);
            List<SpOrderGoodsVO> spOrderGoodsVOList = new ArrayList<>();
            for (SpOrderGoods spOrderGoods : spOrderGoodsList) {
                SpOrderGoodsVO spOrderGoodsVO = new SpOrderGoodsVO();
                SpGoods spGoods = this.spGoodsMapper.selectById(spOrderGoods.getGoodsId());
                BeanUtils.copyProperties(spGoods, spOrderGoodsVO);
                BeanUtils.copyProperties(spOrderGoods, spOrderGoodsVO);
                spOrderGoodsVOList.add(spOrderGoodsVO);
            }
            spOrderVO.setSpOrderGoodsList(spOrderGoodsVOList);
            spOrderVOList.add(spOrderVO);
        }
        return spOrderVOList;
    }
}
