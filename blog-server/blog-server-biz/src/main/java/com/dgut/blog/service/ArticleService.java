package com.dgut.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dgut.blog.entity.Article;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章实体类service服务
 * @createDate: 2021/2/25
 */
public interface ArticleService extends IService<Article> {
    boolean addNewArticle(Article article);
}
