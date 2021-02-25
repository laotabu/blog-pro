package com.dgut.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dgut.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章类别mapper文件
 * @createDate: 2021/2/25
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
//    int deleteTagsByAid(Long aid);
//
//    int saveTags(@Param("tags") String[] tags);
//
//    List<Long> getTagsIdByTagName(@Param("tagNames") String[] tagNames);
//
//    int saveTags2ArticleTags(@Param("tagIds") List<Long> tagIds, @Param("aid") Long aid);
}
