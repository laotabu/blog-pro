package com.dgut.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dgut.blog.vo.ArticleTag;
import org.apache.ibatis.annotations.Mapper;

/***
 *
 *@author: lishengdian / 932978775@qq.com
 *@date: 2/25/2021 
 *@description: 文章与文章类别连接表mapper文件
 *
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {
}
