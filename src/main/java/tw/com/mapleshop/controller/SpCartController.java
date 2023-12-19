package tw.com.mapleshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import tw.com.mapleshop.entity.SpCart;
import tw.com.mapleshop.entity.SpOrder;
import tw.com.mapleshop.entity.SpUser;
import tw.com.mapleshop.exception.SpException;
import tw.com.mapleshop.result.ResponseEnum;
import tw.com.mapleshop.service.SpCartService;
import tw.com.mapleshop.service.SpCategoryService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/spCart")
@Slf4j
public class SpCartController {

    @Autowired
    private SpCartService spCartService;

    @Autowired
    private SpCategoryService spCategoryService;

    // 添加購物車
    @GetMapping("/add/{goodsId}/{goodsPrice}/{quantity}")
    public String add(@PathVariable("goodsId") Integer goodsId,
                            @PathVariable("goodsPrice") Float goodsPrice,
                            @PathVariable("quantity") Integer quantity,
                            HttpSession session
    ) {
        if (goodsId == null || goodsPrice == null || quantity == null) {
            log.info("【添加購物車】商品參數為空");
            throw new SpException(ResponseEnum.PARAMETER_NULL);
        }
        //判斷是否為登入用戶
        SpUser spUser = (SpUser) session.getAttribute("spUser");
        if (spUser == null) {
            log.info("【添加購物車】用戶未登入");
            throw new SpException(ResponseEnum.NOT_LOGIN);
        }
        SpCart spCart = new SpCart();
        spCart.setUserId(spUser.getUserId());
        spCart.setGoodsId(goodsId);
        spCart.setQuantity(quantity);
        spCart.setCost(goodsPrice * quantity);
        Boolean add = this.spCartService.add(spCart);
        if (!add) {
            log.info("【添加購物車】添加失敗");
            throw new SpException(ResponseEnum.CARTADD_ERROR);
        }

        return "redirect:/spCart/get";
    }

    //查看購物車
    @GetMapping("/get")
    public ModelAndView getCart(HttpSession session) {
        //判斷是否為登入用戶
        SpUser spUser = (SpUser) session.getAttribute("spUser");
        if (spUser == null) {
            log.info("【添加購物車】用戶未登入");
            throw new SpException(ResponseEnum.NOT_LOGIN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shoping-cart");
        modelAndView.addObject("cartList", this.spCartService.findVOListByUserId(spUser.getUserId()));
        modelAndView.addObject("list", this.spCategoryService.buildCategoryMenu());
        return modelAndView;
    }

    // 更新購物車
    @PostMapping("/update/{id}/{quantity}/{cost}")
    @ResponseBody
    public String update(@PathVariable("id") Integer id,
                         @PathVariable("quantity") Integer quantity,
                         @PathVariable("cost") Float cost,
                         HttpSession session
    ) {
        if (id == null || quantity == null || cost == null) {
            log.info("【更新購物車】參數為空");
            throw new SpException(ResponseEnum.PARAMETER_NULL);
        }
        //判斷是否為登入用戶
        SpUser spUser = (SpUser) session.getAttribute("spUser");
        if (spUser == null) {
            log.info("【更新購物車】用戶未登入");
            throw new SpException(ResponseEnum.NOT_LOGIN);
        }
        if (this.spCartService.update(id, quantity, cost)) return "success";
        return "fail";
    }

    //刪除購物車
    @GetMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable("id") Integer id,HttpSession session) {
        if (id == null) {
            log.info("【更新購物車】參數為空");
            throw new SpException(ResponseEnum.PARAMETER_NULL);
        }
        //判斷是否為登入用戶
        SpUser spUser = (SpUser) session.getAttribute("spUser");
        if (spUser == null) {
            log.info("【更新購物車】用戶未登入");
            throw new SpException(ResponseEnum.NOT_LOGIN);
        }
        Boolean delete = this.spCartService.delete(id);
        if (delete) return "redirect:/spCart/get";
        return null;
    }

    //確認訂單
    @GetMapping("/checkout")
    public ModelAndView checkout(HttpSession session) {
        //判斷是否為登入用戶
        SpUser spUser = (SpUser) session.getAttribute("spUser");
        if (spUser == null) {
            log.info("【更新購物車】用戶未登入");
            throw new SpException(ResponseEnum.NOT_LOGIN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("checkout");
        modelAndView.addObject("cartList", this.spCartService.findVOListByUserId(spUser.getUserId()));
        modelAndView.addObject("list", this.spCategoryService.buildCategoryMenu());
        return modelAndView;
    }

    //確認訂單
    @PostMapping("/commit")
    public ModelAndView commit(String cgnAddress, HttpSession session) {
        if (cgnAddress == null) {
            log.info("【更新購物車】參數為空");
            throw new SpException(ResponseEnum.PARAMETER_NULL);
        }
        //判斷是否為登入用戶
        SpUser spUser = (SpUser) session.getAttribute("spUser");
        if (spUser == null) {
            log.info("【更新購物車】用戶未登入");
            throw new SpException(ResponseEnum.NOT_LOGIN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contact");
        SpOrder spOrder = this.spCartService.commit(cgnAddress,spUser);
        if (spOrder != null) {
            modelAndView.addObject("order", spOrder);
            modelAndView.addObject("cartList", this.spCartService.findVOListByUserId(spUser.getUserId()));
            return modelAndView;
        }
        return null;
    }
}

