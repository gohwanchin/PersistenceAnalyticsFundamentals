package sg.edu.nus.mini_project;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sg.edu.nus.mini_project.filter.AuthFilter;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> registerFilter() {
        FilterRegistrationBean<AuthFilter> regBean = new FilterRegistrationBean<>();
        regBean.setFilter(new AuthFilter());
        regBean.addUrlPatterns("/t/*");
        return regBean;
    }
}
