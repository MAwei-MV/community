package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //userId为0时，不考虑该属性
    //offset表示起始行的行号
    //limit表示每一列最多显示多少数据，支持分页
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);
    //查询一共多少条数据
    //@Param表示别名，只有一个变量并且在<if>里使用时，必需取别名
    int selectDiscussPostRows(@Param("userId") int userId);
}
