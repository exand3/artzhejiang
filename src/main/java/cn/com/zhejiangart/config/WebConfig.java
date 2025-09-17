package cn.com.zhejiangart.config;


import cn.com.zhejiangart.interceptor.TokenVerifyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public TokenVerifyInterceptor tokenVerifyInterceptor() {
        return new TokenVerifyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenVerifyInterceptor())
//                .addPathPatterns("/**")  // 拦截所有请求
                .excludePathPatterns(
                        "/**"
                );
    }
}
