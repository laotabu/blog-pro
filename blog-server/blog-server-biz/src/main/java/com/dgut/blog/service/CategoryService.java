package com.dgut.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dgut.blog.entity.Category;

import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章栏目service服务
 * @createDate: 2021/2/25
 */
public interface CategoryService extends IService<Category> {

    List<Category> getAllCategories();

    boolean deleteCategoryByIds(List<Long> ids);

    boolean updateCategoryById(Category category);

    boolean addCategory(Category category);
}
