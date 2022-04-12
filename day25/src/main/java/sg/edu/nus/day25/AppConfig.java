package sg.edu.nus.day25;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sg.edu.nus.day25.filter.AuthFilter;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> registerFilter() {
        FilterRegistrationBean<AuthFilter> regBean = new FilterRegistrationBean<>();
        regBean.setFilter(new AuthFilter());
        regBean.addUrlPatterns("/protected/*");
        return regBean;
    }
}
