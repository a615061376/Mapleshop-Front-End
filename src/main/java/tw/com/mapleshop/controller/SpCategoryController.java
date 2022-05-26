package tw.com.mapleshop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import tw.com.mapleshop.entity.SpUser;
import tw.com.mapleshop.service.SpCartService;
import tw.com.mapleshop.service.SpCategoryService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Controller
@RequestMapping("/spCategory")
public class SpCategoryController {

    @Autowired
    private SpCategoryService spCategoryService;

    @Autowired
    private SpCartService spCartService;


    // 首頁數據
    @GetMapping("/index")
    public ModelAndView index(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        //封裝商品分類菜單
        modelAndView.addObject("list", this.spCategoryService.buildCategoryMenu());
        //封裝一級分類對應之商品資訊
        modelAndView.addObject("levelOneGoodsList", this.spCategoryService.findAllGoodsByCategoryLevelOne());
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
        return modelAndView;
    }

}

