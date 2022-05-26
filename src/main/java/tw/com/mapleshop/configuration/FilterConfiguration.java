package tw.com.mapleshop.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tw.com.mapleshop.filter.SpUserFilter;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean registrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new SpUserFilter());
        filterRegistrationBean.addUrlPatterns("/spCart/*","/spUser/personal","/spUser/orderList","/spUser/address");
        return filterRegistrationBean;
    }
}
