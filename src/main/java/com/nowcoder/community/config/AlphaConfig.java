package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

//该注解表示该类是一个配置类
@Configuration
public class AlphaConfig {
    //bean名即为方法名，以simpledateformat为例
    @Bean
    //以下表示返回的对象会被装配到Bean容器中
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
