package com.dgut.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dgut.blog.entity.Article;
import com.dgut.blog.entity.Tag;
import com.dgut.blog.mapper.ArticleMapper;
import com.dgut.blog.service.ArticleService;
import com.dgut.blog.utls.CustomUtils;
import com.dgut.blog.vo.ArticleTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章实体类service服务实现类
 * @createDate: 2021/2/25
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    CustomUtils customUtils;

    @Autowired
    ArticleTagServiceImpl articleTagService;

    @Autowired
    TagServiceImpl tagService;

    @Autowired
    ArticleMapper articleMapper;

    /**
     *  增加或更新文章
     * @param article
     * @return
     */
    @Override
    public boolean addNewArticle(Article article) {
        //处理文章摘要
        if (article.getSummary() == null || "".equals(article.getSummary())) {
            //直接截取
            article.setSummary(customUtils
                    .subStringHtmlContent(customUtils
                            .stripHtml(article.getHtmlContent()), 50));
        }
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 获取文章标签
        List<String> dynamicTags = article.getDynamicTags();
        // 判断文章是否有效
        if (article.getState() == 1) {
            //设置发表日期
            article.setPublishDate(now);
        }
        // 设置编辑时间
        article.setEditTime(now);
        // 设置当前用户
        article.setUserId(customUtils
                .getCurrentUser()
                .getId()
        );
        if (dynamicTags != null && dynamicTags.isEmpty()) {
            // 给文章重新设置标签
            addTagsToArticle(dynamicTags, article.getId());
        }
        return  this.saveOrUpdate(article);
    }

    /**
     * 根据文章标题（模糊字段）和用户Id获取有效的文章数量
     * @param state 文章状态
     * @param userId 用户Id
     * @param keywords 文章标题（模糊字段）
     * @return
     */
    @Override
    public int getArticleCountByStateAndKeywords(Integer state, Long userId, String keywords) {
        return articleMapper.getArticleCountByStateAndKeywords(state, userId, keywords);
    }

    /**
     * 根据文章Id批量更新文章状态
     * @param articleIds
     * @param state
     * @return
     */
    @Override
    public boolean updateArticleStateByIds(List<Long> articleIds, Integer state) {
        return this.lambdaUpdate()
                .in(Article::getId, articleIds)
                .set(Article::getState, state)
                .update();
    }

    /**
     * 根据文章Id更新文章状态
     * @param articleId
     * @param state
     * @return
     */
    @Override
    public boolean updateArticleStateById(Integer articleId, Integer state) {
        return this.lambdaUpdate()
                .eq(Article::getId, articleId)
                .set(Article::getState, state)
                .update();
    }

    /***
     * 根据用户Id，文章状态批量获取文章列表
     * @param state 文章状态
     * @param start 文章列表起始节点
     * @param count 文章数量
     * @param userId 用户id
     * @param keywords 文章标题（模糊字段）
     * @return
     */
    @Override
    public List<Article> getArticlesByStateAndCountAndUserId(Integer state, Integer start, Integer count, Long userId, String keywords) {
        return articleMapper.getArticlesByStateAndCountAndUserId(state, start, count, userId, keywords);
    }

    /***
     * 根据文章Id获取文章信息
     * @param articleId
     * @return
     */
    @Override
    public Article getArticleByArticleId(Long articleId) {
        return articleMapper.getArticleByArticleId(articleId);
    }

    /***
     * 增加文章阅读量
     * @param articleId
     */
    @Override
    public void userViewIncrement(Long articleId) {
        articleMapper.userViewIncrement(articleId);
    }



    private void addTagsToArticle(List<String> dynamicTags, Long aid) {

        // 根据文章Id获取TagId列表
        List<Long> tagIds = articleTagService.getTagIdsByArticleId(aid);

        // 根据文章Id删除原先关联记录(articleTag)
        articleTagService.removeByArticleId(aid);

        // 根据文章Id删除对应的Tags
        tagService.removeByIds(tagIds);

        // 生成tags列表
        List<Tag> tags = new ArrayList<Tag>();
        dynamicTags.forEach(item -> {
            Tag tag = new Tag();
            tag.setTagName(item);
            tags.add(tag);
        });

        //存储tags
        tagService.saveBatch(tags);

        // 输出tags列表(可删掉，测试用)
        tags.stream().forEach(System.out::println);

//        //3.查询这些标签的id
//        List<Long> tIds = tagsMapper.getTagsIdByTagName(dynamicTags);

        // 获取新的Tag的Id列表
        List<Long> newTagIds = tags.stream().map(Tag::getId).collect(Collectors.toList());

        // 生成tag与article的关联列表
        List<ArticleTag> articleTags = new ArrayList<>();
        newTagIds.forEach(item -> {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(aid);
            articleTag.setTagId(item);
            articleTags.add(articleTag);
        });
        // 存储关联信息
        articleTagService.saveBatch(articleTags);
    }



}
