package tw.com.mapleshop.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import tw.com.mapleshop.entity.SpConsignee;
import tw.com.mapleshop.entity.SpUser;
import tw.com.mapleshop.exception.SpException;
import tw.com.mapleshop.form.SpConsigneeForm;
import tw.com.mapleshop.result.ResponseEnum;
import tw.com.mapleshop.service.SpCartService;
import tw.com.mapleshop.service.SpCategoryService;
import tw.com.mapleshop.service.SpConsigneeService;
import tw.com.mapleshop.utils.RegexValidateUtil;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 * 收貨人表 前端控制器
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Controller
@RequestMapping("/spConsignee")
@Slf4j
public class SpConsigneeController {

    @Autowired
    private SpConsigneeService spConsigneeService;

    @Autowired
    private SpCartService spCartService;

    @Autowired
    private SpCategoryService spCategoryService;

    @PostMapping("/input")
    public String input(@Valid SpConsigneeForm spConsigneeForm,HttpSession session,BindingResult bindingResult) {
        //判斷是否為登入用戶
        SpUser spUser = (SpUser) session.getAttribute("spUser");
        if (spUser == null) {
            log.info("【更新購物車】用戶未登入");
            throw new SpException(ResponseEnum.NOT_LOGIN);
        }
        // 非空校驗
        if (bindingResult.hasErrors()) {
            log.info("【訂單資訊】收貨人資訊不能為空");
            throw new SpException(ResponseEnum.CGN_INFO_NULL);
        }
        SpConsignee input = this.spConsigneeService.input(spConsigneeForm, spUser);
        if (input == null) {
            log.info("【訂單資訊】訂單資訊添加失敗");
            throw new SpException(ResponseEnum.CGN_INFO_ERROR);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cartList", this.spCartService.findVOListByUserId(spUser.getUserId()));
        modelAndView.addObject("list", this.spCategoryService.buildCategoryMenu());
        return "forward:/spCart/commit";
    }
}

