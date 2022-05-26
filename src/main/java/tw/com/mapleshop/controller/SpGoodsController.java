package tw.com.mapleshop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import tw.com.mapleshop.entity.SpCart;
import tw.com.mapleshop.entity.SpGoods;
import tw.com.mapleshop.entity.SpUser;
import tw.com.mapleshop.exception.SpException;
import tw.com.mapleshop.result.ResponseEnum;
import tw.com.mapleshop.service.SpCartService;
import tw.com.mapleshop.service.SpCategoryService;
import tw.com.mapleshop.service.SpGoodsService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Controller
@RequestMapping("/spGoods")
@Slf4j
public class SpGoodsController {

    @Autowired
    private SpGoodsService spGoodsService;

    @Autowired
    private SpCategoryService spCategoryService;

    @Autowired
    private SpCartService spCartService;

    // 商品列表
    @GetMapping("/list/{catLevel}/{catId}")
    public ModelAndView list(@PathVariable("catLevel") Integer catLevel,
                             @PathVariable("catId") Integer catId,
                             HttpSession session)
    {
        if (catLevel == null || catId == null) {
            log.info("【商品列表】商品參數為空");
            throw new SpException(ResponseEnum.PARAMETER_NULL);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop-grid");
        modelAndView.addObject("spGoodsList", this.spGoodsService.findAllByLevelAndSpCategoryId(catLevel, catId));
        //判斷是否為登入用戶
        SpUser spUser = (SpUser) session.getAttribute("spUser");
        if (spUser == null) {
            //未登入
            modelAndView.addObject("cartList",new ArrayList<>());
        } else {
            //已登入
            //查詢該用戶的購物車紀錄
            modelAndView.addObject("cartList", this.spCartService.findVOListByUserId(spUser.getUserId()));
        }
        //商品分類
        modelAndView.addObject("list", this.spCategoryService.buildCategoryMenu());
        return modelAndView;
    }

    // 搜尋功能
    @PostMapping("/search")
    public ModelAndView search(String keyWord, HttpSession session) {
        if (keyWord == null ) {
            log.info("【商品搜尋】商品參數為空");
            throw new SpException(ResponseEnum.PARAMETER_NULL);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop-grid");
        //模糊查詢的數據
        QueryWrapper<SpGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("goods_name", keyWord);
        modelAndView.addObject("spGoodsList", this.spGoodsService.list(queryWrapper));
        //判斷是否為登入用戶
        SpUser spUser = (SpUser) session.getAttribute("spUser");
        if (spUser == null) {
            //未登入
            modelAndView.addObject("cartList",new ArrayList<>());
        } else {
            //已登入
            //查詢該用戶的購物車紀錄
            modelAndView.addObject("cartList", this.spCartService.findVOListByUserId(spUser.getUserId()));
        }
        //商品分類
        modelAndView.addObject("list", this.spCategoryService.buildCategoryMenu());
        return modelAndView;
    }

    // 商品資訊
    @GetMapping("/details/{goodsId}")
    public ModelAndView details(@PathVariable("goodsId") Integer goodsId, HttpSession session) {
        if (goodsId == null ) {
            log.info("【商品詳細訊息】商品參數為空");
            throw new SpException(ResponseEnum.PARAMETER_NULL);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop-details");
        //判斷是否為登入用戶
        SpUser spUser = (SpUser) session.getAttribute("spUser");
        if (spUser == null) {
            //未登入
            modelAndView.addObject("cartList",new ArrayList<>());
        } else {
            //已登入
            //查詢該用戶的購物車紀錄
            modelAndView.addObject("cartList", this.spCartService.findVOListByUserId(spUser.getUserId()));
        }
        //商品分類
        modelAndView.addObject("list", this.spCategoryService.buildCategoryMenu());
        //商品資訊
        modelAndView.addObject("spGoods", this.spGoodsService.getById(goodsId));
        return modelAndView;
    }

}

