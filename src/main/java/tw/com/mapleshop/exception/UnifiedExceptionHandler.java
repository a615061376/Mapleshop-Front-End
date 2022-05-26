package tw.com.mapleshop.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import tw.com.mapleshop.result.ResponseEnum;

@RestControllerAdvice
public class UnifiedExceptionHandler {

    @ExceptionHandler(value = SpException.class)
    public ModelAndView handlerException(SpException e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        ResponseEnum responseEnum = e.getResponseEnum();
        switch (responseEnum.getCode()) {
            case 301:
                modelAndView.setViewName("register");
                modelAndView.addObject("emailError", responseEnum.getMsg());
                break;
            case 302:
                modelAndView.setViewName("register");
                modelAndView.addObject("phoneError", responseEnum.getMsg());
                break;
            case 303:
                modelAndView.setViewName("register");
                modelAndView.addObject("userNameExists", responseEnum.getMsg());
                break;
            case 305:
                modelAndView.setViewName("login");
                modelAndView.addObject("userNameError", responseEnum.getMsg());
                break;
            case 306:
                modelAndView.setViewName("login");
                modelAndView.addObject("passwordError", responseEnum.getMsg());
                break;
            case 308:
                modelAndView.setViewName("login");
                break;
        }
        return  modelAndView;
    }
}
