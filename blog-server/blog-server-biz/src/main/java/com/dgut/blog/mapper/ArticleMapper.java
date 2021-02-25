package com.dgut.blog.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dgut.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章实体类mapper文件
 * @createDate: 2021/2/25
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {



//    List<Article> getArticleByState(@Param("state") Integer state, @Param("start") Integer start, @Param("count") Integer count, @Param("uid") Long uid, @Param("keywords") String keywords);
//
////    List<Article> getArticleByStateByAdmin(@Param("start") int start, @Param("count") Integer count, @Param("keywords") String keywords);
//
//    int getArticleCountByState(@Param("state") Integer state, @Param("uid") Long uid, @Param("keywords") String keywords);
//
//    int updateArticleState(@Param("aids") Long aids[], @Param("state") Integer state);
//
//    int updateArticleStateById(@Param("articleId") Integer articleId, @Param("state") Integer state);
//
//    int deleteArticleById(@Param("aids") Long[] aids);
//
//    Article getArticleById(Long aid);
//
//    void pvIncrement(Long aid);
//
//    //INSERT INTO pv(countDate,pv,uid) SELECT NOW(),SUM(pageView),uid FROM article GROUP BY uid
//    void pvStatisticsPerDay();
//
//    List<String> getCategories(Long uid);
//
//    List<Integer> getDataStatistics(Long uid);
}
