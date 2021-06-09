package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//作用范围，表示整个容器中可以有多少个对象，默认是singleton（单例），prototype表示多例(没必要)
//@Scope("prototype")
//此处演示管理bean的初始化和销毁
public class AlphaService {
    public AlphaService(){
        System.out.println("实例化AlphaService");
    }
    //该注解表示下面的方法会在构造器调用之后调用
    @PostConstruct
    public void init(){
        System.out.println("初始化AlphaService");
    }
    //该注解表示在销毁对象之前调用以下方法，可以在方法中释放某些资源
    @PreDestroy
    public void destroy(){
        System.out.println("销毁AlphaService");
    }
    //service访问Dao
    @Autowired
    private AlphaDao alphaDao;

    public String find(){
        return alphaDao.select();
    }
}
