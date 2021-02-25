package com.dgut.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dgut.blog.entity.Article;
import com.dgut.blog.entity.Category;
import com.dgut.blog.mapper.ArticleMapper;
import com.dgut.blog.mapper.CategoryMapper;
import com.dgut.blog.service.ArticleService;
import com.dgut.blog.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章栏目类service服务实现类
 * @createDate: 2021/2/25
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
