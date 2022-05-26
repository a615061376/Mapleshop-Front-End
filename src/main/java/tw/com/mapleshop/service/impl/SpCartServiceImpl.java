package tw.com.mapleshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tw.com.mapleshop.entity.*;
import tw.com.mapleshop.exception.SpException;
import tw.com.mapleshop.mapper.SpCartMapper;
import tw.com.mapleshop.mapper.SpGoodsMapper;
import tw.com.mapleshop.mapper.SpOrderGoodsMapper;
import tw.com.mapleshop.mapper.SpOrderMapper;
import tw.com.mapleshop.result.ResponseEnum;
import tw.com.mapleshop.service.SpCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tw.com.mapleshop.vo.SpCartVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JHuang
 * @since 2022-02-20
 */
@Service
@Slf4j
public class SpCartServiceImpl extends ServiceImpl<SpCartMapper, SpCart> implements SpCartService {

    @Autowired
    private SpCartMapper spCartMapper;

    @Autowired
    private SpGoodsMapper spGoodsMapper;

    @Autowired
    private SpOrderMapper spOrderMapper;

    @Autowired
    private SpOrderGoodsMapper spOrderGoodsMapper;

    @Override
    @Transactional
    public Boolean add(SpCart spCart) {
        //添加購物車
        int insert = this.spCartMapper.insert(spCart);
        if (insert != 1) {
            throw new SpException(ResponseEnum.CARTADD_ERROR);
        }
        // 商品庫存減少(更新)
        Integer number = this.spGoodsMapper.getNumberById(spCart.getGoodsId());
        if (number == null) {
            log.info("【添加購物車】商品不存在");
            throw new SpException(ResponseEnum.GOODS_NOT_EXISTS);
        }
        if (number == 0) {
            log.info("【添加購物車】商品庫存不足");
            throw new SpException(ResponseEnum.GOODS_NUMBER_ERROR);
        }
        Integer newNumber = number - spCart.getQuantity();
        if (newNumber < 0 ) {
            log.info("【添加購物車】商品庫存不足");
            throw new SpException(ResponseEnum.GOODS_NUMBER_ERROR);
        }
        this.spGoodsMapper.updateNumberById(spCart.getGoodsId(), newNumber);
        return true;
    }

    @Override
    public List<SpCartVO> findVOListByUserId(Integer userId) {
        QueryWrapper<SpCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<SpCart> spCartList = this.spCartMapper.selectList(queryWrapper);
        List<SpCartVO> cartVOList = new ArrayList<>();
        for (SpCart spCart : spCartList) {
            SpGoods spGoods = this.spGoodsMapper.selectById(spCart.getGoodsId());
            SpCartVO spCartVO = new SpCartVO();
            BeanUtils.copyProperties(spGoods, spCartVO);
            BeanUtils.copyProperties(spCart, spCartVO);
            cartVOList.add(spCartVO);
        }
        return cartVOList;
    }

    @Override
    @Transactional
    public Boolean update(Integer id, Integer quantity, Float cost) {
        //更新庫存
//        int oldQuantity = this.spCartMapper.getQuantityById(id);
        SpCart spCart = this.spCartMapper.selectById(id);
        Integer oldQuantity = spCart.getQuantity();
        if (quantity.equals(oldQuantity)) {
            log.info("【更新購物車】參數錯誤");
            throw new SpException(ResponseEnum.CART_UPDATE_PARAMETER_ERROR);
        }
        //查詢商品庫存
        // 原庫存 - (更新後購買數量 - 更新前購買數量) = 更新後庫存
        Integer goodsNumber = this.spGoodsMapper.getNumberById(spCart.getGoodsId());
        Integer newGoodsNumber = goodsNumber - (quantity - oldQuantity);
        if (newGoodsNumber < 0 ) {
            log.info("【更新購物車】商品庫存錯誤");
            throw new SpException(ResponseEnum.GOODS_NUMBER_ERROR);
        }
        Integer integer = this.spGoodsMapper.updateNumberById(spCart.getGoodsId(), newGoodsNumber);
        if (integer != 1) {
            log.info("【更新購物車】更新商品庫存失敗");
            throw new SpException(ResponseEnum.CART_UPDATE_ERROR);
        }
        // 更新數據
        Integer update = this.spCartMapper.update(id, quantity, cost);
        if (update != 1) {
            log.info("【更新購物車】更新失敗");
            throw new SpException(ResponseEnum.CART_UPDATE_ERROR);
        }
        return true;
    }

    @Override
    public Boolean delete(Integer id) {
        //更新商品庫存
        SpCart spCart = this.spCartMapper.selectById(id);
        Integer goodsNumber = this.spGoodsMapper.getNumberById(spCart.getGoodsId());
        Integer newGoodsNumber = goodsNumber + spCart.getQuantity();
        Integer integer = this.spGoodsMapper.updateNumberById(spCart.getGoodsId(), newGoodsNumber);
        if (integer != 1) {
            log.info("【刪除購物車】更新商品庫存失敗");
            throw new SpException(ResponseEnum.CART_UPDATE_STOCK_ERROR);
        }
        //刪除購物車紀錄
        int i = this.spCartMapper.deleteById(id);
        if (i != 1) {
            log.info("【刪除購物車】刪除失敗");
            throw new SpException(ResponseEnum.CART_REMOVE_ERROR);
        }
        return true;
    }

    @Override
    @Transactional
    public SpOrder commit(String cgnAddress, SpUser spUser) {
        // 創建主訂單
        SpOrder spOrder = new SpOrder();
        spOrder.setUserId(spUser.getUserId());
        spOrder.setCgnAddress(cgnAddress);
        spOrder.setOrderPrice(this.spCartMapper.getCostByUserId(spUser.getUserId()));
        String serialNumber = null;
        try {
            StringBuffer result = new StringBuffer();
            for (int i=0; i < 32; i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            serialNumber = result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        spOrder.setOrderNumber(serialNumber);
        int insert = this.spOrderMapper.insert(spOrder);
        if (insert != 1) {
            log.info("【確認訂單】創建訂單失敗");
            throw new SpException(ResponseEnum.ORDERS_CREATE_ERROR);
        }
        //創建訂單詳情表
        QueryWrapper<SpCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", spUser.getUserId());
        List<SpCart> spCartList = this.spCartMapper.selectList(queryWrapper);
        for (SpCart spCart : spCartList) {
            SpOrderGoods spOrderGoods = new SpOrderGoods();
            spOrderGoods.setOrderId(spOrder.getOrderId());
            spOrderGoods.setGoodsId(spCart.getGoodsId());
            spOrderGoods.setGoodsNumber(spCart.getQuantity());
            spOrderGoods.setGoodsTotalPrice(spCart.getCost());
            int insert1 = this.spOrderGoodsMapper.insert(spOrderGoods);
            if (insert1 == 0) {
                log.info("【確認訂單】創建訂單詳情表失敗");
                throw new SpException(ResponseEnum.ORDERGOODS_CREATE_ERROR);
            }
        }
        // 清空當前購物車
        QueryWrapper<SpCart> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id", spUser.getUserId());
        int delete = this.spCartMapper.delete(queryWrapper1);
        if (delete == 0) {
            log.info("【確認訂單】清空購物車失敗");
            throw new SpException(ResponseEnum.CART_REMOVE_ERROR);
        }
        return spOrder;
    }
}
