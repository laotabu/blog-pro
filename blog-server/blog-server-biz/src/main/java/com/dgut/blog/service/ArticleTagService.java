package com.dgut.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dgut.blog.entity.Article;
import com.dgut.blog.vo.ArticleTag;

import java.util.List;
import java.util.Set;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章与文章类别连接表service服务
 * @createDate: 2021/2/25
 */
public interface ArticleTagService extends IService<ArticleTag> {

    boolean removeByArticleId(Long articleId);

    List<Long> getTagIdsByArticleId(Long articleId);
}
