package com.dgut.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dgut.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章栏目实体类mapper文件
 * @createDate: 2021/2/25
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
//    List<Category> getAllCategories();
//
//    int deleteCategoryByIds(@Param("ids") String[] ids);
//
//    int updateCategoryById(Category category);
//
//    int addCategory(Category category);
}
