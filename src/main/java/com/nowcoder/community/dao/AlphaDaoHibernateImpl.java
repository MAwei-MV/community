package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

//括号内是自定义的bean名字，默认是类名
@Repository("alphaHibernate")
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select() {
        return "hibernate";
    }
}
