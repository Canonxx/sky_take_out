package com.hubu.config;

import com.hubu.interceptor.JwtTokenAdminInterceptor;
import com.hubu.json.JacksonObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    /**
     * 注册自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("注册自定义拦截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login",
                        "/doc.html",
                        "/webjars/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**");
    }

    /**
     * 添加自定义消息转换器（JSON序列化配置）
     */
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        log.info("扩展消息转换器...");
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setObjectMapper(new JacksonObjectMapper());
//        converters.add(0, converter); // 优先使用自定义转换器
//    }

    /**
     * Knife4j / Swagger 静态资源映射配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("注册 Swagger / Knife4j 静态资源映射...");

        // Knife4j 主页面
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        // Swagger 3.x 新路径
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        // WebJar 资源
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        // Swagger 元数据
        registry.addResourceHandler("/v3/api-docs/**")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    /**
     * Swagger / Knife4j API 基本信息
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("苍穹外卖项目接口文档")
                        .version("2.0")
                        .description("苍穹外卖项目接口文档")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://doc.xiaominfo.com")));
    }
}
