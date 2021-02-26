package com.dgut.blog.controller.admin;


import com.dgut.blog.dto.ResponseDTO;
import com.dgut.blog.entity.Article;
import com.dgut.blog.service.ArticleService;
import com.dgut.blog.utls.CustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 管理员模块 - 管理文章
 * @createDate: 2021/2/25
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CustomUtils customUtils;

    /**
     * 获取指定页面文章
     * @param page
     * @param count
     * @param keywords
     * @return
     */
    @RequestMapping(value = "/article/all", method = RequestMethod.GET)
    public Map<String, Object> getArticleByStateByAdmin(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords) {
        List<Article> articles = articleService
                .getArticlesByStateAndCountAndUserId(-2,
                        page,
                        count,
                        customUtils.getCurrentUser().getId(),
                        keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("articles", articles);
        map.put("totalCount",
                articleService
                        .getArticleCountByStateAndKeywords(1,
                            null,
                            keywords));
        return map;
    }

    /**
     * 批量删除文章
     * @param aids
     * @param state
     * @return
     */
    @RequestMapping(value = "/article/dustbin", method = RequestMethod.PUT)
    public ResponseDTO updateArticleState(List<Long> aids, Integer state) {
        System.out.println("文章编号" + aids);
        if (articleService.updateArticleStateByIds(aids, state)) {
            return new ResponseDTO("success", "删除成功!");
        }
        return new ResponseDTO("error", "删除失败!");
    }
}
