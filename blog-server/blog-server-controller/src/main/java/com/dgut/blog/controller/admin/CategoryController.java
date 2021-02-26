package com.dgut.blog.controller.admin;

import com.dgut.blog.dto.ResponseDTO;
import com.dgut.blog.entity.Category;
import com.dgut.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章栏目controller，只有管理员才能调用
 * @createDate: 2021/2/25
 */
@RestController
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 获取所有文章栏目
     * @return
     */
    @RequestMapping(value = "/category/all", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return categoryService.getAllCategory();
    }

    /**
     *  根据id删除文章栏目
     * @param ids
     * @return
     */
    @RequestMapping(value = "/category/{ids}", method = RequestMethod.DELETE)
    public ResponseDTO deleteById(@PathVariable String ids) {
        List<String> strings = Arrays.asList(ids.split(","));
        if (!strings.isEmpty()) {
            List<Long> idList = new ArrayList<>();
            strings.stream().forEach(item->{
                idList.add(Long.valueOf(item));
            });
            if (categoryService.deleteCategoryByIds(idList)) {
                return new ResponseDTO("success", "删除成功!");
            }else {
                return new ResponseDTO("error", "删除失败!");
            }
        }
        return new ResponseDTO("error", "参数不合格!");
    }

    /**
     * 增加文章栏目
     * @param category
     * @return
     */
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public ResponseDTO addNewCate(Category category) {
        if ("".equals(category.getCateName()) || category.getCateName() == null) {
            return new ResponseDTO("error", "请输入栏目名称!");
        }
         if (categoryService.addCategory(category)) {
            return new ResponseDTO("success", "添加成功!");
        }
        return new ResponseDTO("error", "添加失败!");
    }


    /**
     * 修改文章栏目
     * @param category
     * @return
     */
    @RequestMapping(value = "/category", method = RequestMethod.PUT)
    public ResponseDTO updateCate(Category category) {
        if (categoryService.updateCategoryById(category)) {
            return new ResponseDTO("success", "修改成功!");
        }
        return new ResponseDTO("error", "修改失败!");
    }
}
