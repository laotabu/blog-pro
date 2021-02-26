package com.dgut.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dgut.blog.entity.Tag;
import com.dgut.blog.mapper.TagMapper;
import com.dgut.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章类别实体类service服务实现类
 * @createDate: 2021/2/25
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {


    /**
     * 根据标签名列表获取标签Id列表
     * @param tagNames 标签名列表
     * @return
     */
    @Override
    public List<Long> getTagsIdByTagName(List<String> tagNames) {
        List<Long> list = new ArrayList<>();
        List<Tag> tags = this.lambdaQuery()
                .in(Tag::getTagName, tagNames)
                .list();
        if (!tags.isEmpty()){
            list = tags.stream().map(Tag::getId).collect(Collectors.toList());
        }
        return list;
    }

    /**
     * 保存Tag标签
     * @param tags
     * @return
     */
    @Override
    public boolean saveTags(List<String> tags) {
        if (!tags.isEmpty()) {
            List<Tag> tagList = new ArrayList<>();
            tags.stream().forEach(item-> {
                Tag tag = new Tag();
                tag.setTagName(item);
                tagList.add(tag);
            });
            return saveBatch(tagList);
        }
        return false;
    }
}
