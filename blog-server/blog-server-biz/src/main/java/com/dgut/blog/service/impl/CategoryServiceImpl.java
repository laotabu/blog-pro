package com.dgut.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dgut.blog.entity.Article;
import com.dgut.blog.entity.Category;
import com.dgut.blog.mapper.ArticleMapper;
import com.dgut.blog.mapper.CategoryMapper;
import com.dgut.blog.service.ArticleService;
import com.dgut.blog.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章栏目类service服务实现类
 * @createDate: 2021/2/25
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    /***
     * 获取所有文章栏目对象
     * @return
     */
    @Override
    public List<Category> getAllCategory() {
        return this.lambdaQuery().list();
    }

    /***
     * 获取所有文章栏目名
     * @return
     */
    @Override
    public List<String> getAllCateName() {
        List<String> strings = new ArrayList<>();
        List<Category> categorys = this.getAllCategory();
        if (!categorys.isEmpty()){
            strings = categorys.stream().map(Category::getCateName).collect(Collectors.toList());
        }
        return strings;
    }

    /**
     * 根据栏目Id删除栏目
     * @param ids 栏目Id
     * @return
     */
    @Override
    public boolean deleteCategoryByIds(List<Long> ids) {
        return this.remove(this.lambdaQuery().in(Category::getId, ids));
    }

    /**
     * 更新栏目
     * @param category 栏目实体类
     * @return
     */
    @Override
    public boolean updateCategoryById(Category category) {
        return this.updateById(category);
    }

    /**
     * 增加栏目
     * @param category 栏目实体类
     * @return
     */
    @Override
    public boolean addCategory(Category category) {
        return this.save(category);
    }
}
