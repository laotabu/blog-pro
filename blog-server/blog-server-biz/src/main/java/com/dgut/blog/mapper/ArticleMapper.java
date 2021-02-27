package com.dgut.blog.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dgut.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章实体类mapper文件
 * @createDate: 2021/2/25
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    int getArticleCountByStateAndKeywords(Integer state, Long userId, String keywords);

    List<Article> getArticlesByStateAndCountAndUserId(Integer state, Integer start, Integer count, Long userId, String keywords);

    Article getArticleByArticleId(Long articleId);

    void userViewIncrement(Long articleId);

    boolean removeArticleByArticleId(List<Long> articleId);
}
