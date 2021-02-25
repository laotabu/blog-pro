package com.dgut.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dgut.blog.entity.Tag;
import com.dgut.blog.mapper.TagMapper;
import com.dgut.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章类别实体类service服务实现类
 * @createDate: 2021/2/25
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public boolean removeTagsByArticleId(Long articleId) {
        return tagMapper.removeTagsByArticleId(articleId);
    }
}
