package tw.com.mapleshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tw.com.mapleshop.entity.SpUser;
import tw.com.mapleshop.service.SpCartService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class RedirectController {

    @Autowired
    private SpCartService spCartService;

    @GetMapping("/{url}")
    public ModelAndView redirect(@PathVariable("url") String url, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);
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

    @GetMapping("/")
    public String main(){
        return "redirect:/spCategory/index";
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }
}
