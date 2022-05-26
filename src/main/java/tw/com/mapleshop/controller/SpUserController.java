package tw.com.mapleshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tw.com.mapleshop.entity.SpUser;
import tw.com.mapleshop.exception.SpException;
import tw.com.mapleshop.form.SpUserLoginForm;
import tw.com.mapleshop.form.SpUserRegisterForm;
import tw.com.mapleshop.result.ResponseEnum;
import tw.com.mapleshop.service.*;
import tw.com.mapleshop.utils.RegexValidateUtil;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 會員表 前端控制器
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Controller
@RequestMapping("/spUser")
@Slf4j
public class SpUserController {

    @Autowired
    private SpUserService spUserService;

    @Autowired
    private SpOrderService spOrderService;

    @Autowired
    private SpCategoryService spCategoryService;

    @Autowired
    private SpCartService spCartService;

    @Autowired
    private SpConsigneeService spConsigneeService;

    @PostMapping("/register")
    public String register(@Valid SpUserRegisterForm spUserRegisterForm, BindingResult bindingResult){
        // 非空校驗
        if (bindingResult.hasErrors()) {
            log.info("【用戶註冊】用戶名不能為空");
            throw new SpException(ResponseEnum.USER_INFO_NULL);
        }
        SpUser register= this.spUserService.register(spUserRegisterForm);
        if (register == null) {
            log.info("【用戶註冊】添加用戶失敗");
            throw new SpException(ResponseEnum.USER_REGISTER_ERROR);
        }
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@Valid SpUserLoginForm spUserLoginForm, BindingResult bindingResult, HttpSession session) {
        // 非空校驗
        if (bindingResult.hasErrors()) {
            log.info("【用戶登入】用戶名不能為空");
            throw new SpException(ResponseEnum.USER_INFO_NULL);
        }
        SpUser login = this.spUserService.login(spUserLoginForm);
        session.setAttribute("spUser", login);
        return "redirect:/spCategory/index";
    }

    @GetMapping("/personal")
    public ModelAndView personal(HttpSession session) {
        //判斷是否為登入用戶
        SpUser spUser = (SpUser) session.getAttribute("spUser");
        if (spUser == null) {
            log.info("【個人設定】用戶未登入");
            throw new SpException(ResponseEnum.NOT_LOGIN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("setting");
        modelAndView.addObject("cartList", this.spCartService.findVOListByUserId(spUser.getUserId()));
        modelAndView.addObject("list", this.spCategoryService.buildCategoryMenu());
        return modelAndView;
    }

    @GetMapping("/orderList")
    public ModelAndView orderList(HttpSession session) {
        //判斷是否為登入用戶
        SpUser spUser = (SpUser) session.getAttribute("spUser");
        if (spUser == null) {
            log.info("【個人設定】用戶未登入");
            throw new SpException(ResponseEnum.NOT_LOGIN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderList");
        modelAndView.addObject("spOrderList", this.spOrderService.findAllByUser(spUser.getUserId()));
        modelAndView.addObject("cartList", this.spCartService.findVOListByUserId(spUser.getUserId()));
        modelAndView.addObject("list", this.spCategoryService.buildCategoryMenu());
        return modelAndView;
    }

    @GetMapping("/address")
    public ModelAndView addressList(HttpSession session) {
        //判斷是否為登入用戶
        SpUser spUser = (SpUser) session.getAttribute("spUser");
        if (spUser == null) {
            log.info("【個人設定】用戶未登入");
            throw new SpException(ResponseEnum.NOT_LOGIN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("address");
        modelAndView.addObject("addressList",this.spConsigneeService.findAddressByUser(spUser.getUserId()) );
        modelAndView.addObject("cartList", this.spCartService.findVOListByUserId(spUser.getUserId()));
        modelAndView.addObject("list", this.spCategoryService.buildCategoryMenu());
        return modelAndView;
    }

}

