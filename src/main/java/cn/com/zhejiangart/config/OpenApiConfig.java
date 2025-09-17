package cn.com.zhejiangart.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("浙江艺术网API文档")
                        .version("1.0")
                        .description("浙江艺术网后端API接口文档")
                        .contact(new Contact()
                                .name("开发团队")
                                .url("http://www.zhejiangart.com")
                                .email("developer@zhejiangart.com"))
                        .license(new License().name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}