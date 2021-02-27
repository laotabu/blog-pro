package com.dgut.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dgut.blog.mapper.ArticleTagMapper;
import com.dgut.blog.service.ArticleTagService;
import com.dgut.blog.vo.ArticleTag;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/***
 *
 *@author: lishengdian / 932978775@qq.com
 *@date: 2/25/2021 
 *@description: 文章与文章类别连接表service服务实现类
 *
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    /**
     * 根据文章Id删除文章与文章类别关联记录
     * @param articleId
     * @return
     */
    @Override
    public boolean removeByArticleId(Long articleId) {
        System.out.println("根据文章Id删除文章与文章类别关联记录");
        Map<String, Object> maps = new HashMap<>();
        maps.put("article_id", articleId);
        return this.removeByMap(maps);
    }

    /**
     * 根据文章Id获取TagId
     * @param articleId
     * @return
     */
    @Override
    public List<Long> getTagIdsByArticleId(Long articleId) {
        List<Long> tagIds = new ArrayList<>();
        List<ArticleTag> articleTags = this.lambdaQuery()
                .in(ArticleTag::getArticleId, articleId)
                .list();
        if (!articleTags.isEmpty()){
            tagIds = articleTags.stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        }
        System.out.println("根据文章Id获取Tag: " + articleTags);
        System.out.println("根据文章Id获取TagIds: " + tagIds);
        return tagIds;
    }
}
