package com.dgut.blog.controller;

import com.dgut.blog.dto.ResponseDTO;
import com.dgut.blog.entity.Article;
import com.dgut.blog.entity.Category;
import com.dgut.blog.service.ArticleService;
import com.dgut.blog.service.CategoryService;
import com.dgut.blog.service.UserViewService;
import com.dgut.blog.utls.CustomUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章管理controller
 * @createDate: 2021/2/25
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CustomUtils customUtils;

    @Autowired
    UserViewService userViewService;

    /**
     * 发表文章
     * @param article
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseDTO addNewArticle(Article article) {
        System.out.println("新增文章" + article);
        if (articleService.addNewArticle(article)) {
            return new ResponseDTO("success", article.getId() + "");
        } else {
            return new ResponseDTO("error", article.getState() == 0 ? "文章保存失败!" : "文章发表失败!");
        }
    }

    /**
     * 上传图片
     * @return 返回值为图片的地址
     */
    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
    public ResponseDTO uploadImg(HttpServletRequest req, MultipartFile image) {
        StringBuffer url = new StringBuffer();
        String filePath = "/blogimg/" + sdf.format(new Date());
        String imgFolderPath = req.getServletContext().getRealPath(filePath);
        File imgFolder = new File(imgFolderPath);
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }
        url.append(req.getScheme())
                .append("://")
                .append(req.getServerName())
                .append(":")
                .append(req.getServerPort())
                .append(req.getContextPath())
                .append(filePath);
        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
        try {
            IOUtils.write(image.getBytes(), new FileOutputStream(new File(imgFolder, imgName)));
            url.append("/").append(imgName);
            return new ResponseDTO("success", url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseDTO("error", "上传失败!");
    }

    /**
     *  根据文章状态获取指定数量的文章
     * @param state
     * @param page
     * @param count
     * @param keywords
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getArticlesByState(@RequestParam(value = "state", defaultValue = "-1") Integer state,
                                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                 @RequestParam(value = "count", defaultValue = "6") Integer count,
                                                  String keywords) {
        //System.out.println("当前用户Id： " + customUtils.getCurrentUser().getId());
        int totalCount = articleService
                .getArticleCountByStateAndKeywords(state,
                        customUtils.getCurrentUser().getId(),
                        keywords);
        List<Article> articles = articleService
                .getArticlesByStateAndCountAndUserId(state,
                        (page - 1) * count,
                        count,
                        customUtils.getCurrentUser().getId(),
                        keywords);
        //System.out.println("对应列表为： " + articles);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("articles", articles);
        return map;
    }

    /**
     * 根据Id获取文章
     * @param aid
     * @return
     */
    @RequestMapping(value = "/{aid}", method = RequestMethod.GET)
    public Article getArticleById(@PathVariable Long aid) {
        return articleService.getArticleByArticleId(aid);
    }

    /**
     * 更新文件状态
     * @param aids
     * @param state
     * @return
     */
    @RequestMapping(value = "/dustbin", method = RequestMethod.PUT)
    public ResponseDTO updateArticleState(Long[] aids, Integer state) {
        List<Long> longs = Arrays.asList(aids);
        System.out.println("删除的文章Id为：");
        longs.stream().forEach(System.out::println);
        if (articleService.updateArticleByStateAndArticleId(longs, state)) {
            return new ResponseDTO("success", "删除成功!");
        }
        return new ResponseDTO("error", "删除失败!");
    }

    /**
     * 恢复文件
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/restore", method = RequestMethod.PUT)
    public ResponseDTO restoreArticle(Long articleId) {
        System.out.println("还原文章开始，文章Id为： " + articleId.toString());
        if (articleService.restoreArticleByArticleId(articleId)) {
            return new ResponseDTO("success", "还原成功!");
        }
        return new ResponseDTO("error", "还原失败!");
    }


    /***
     * 获取栏目浏览量量信息
     * @return
     */
    @RequestMapping("/dataStatistics")
    public Map<String,Object> dataStatistics() {
        Map<String, Object> map = new HashMap<>();
        List<String> categories = categoryService.getAllCateName();
        List<Long> dataStatistics = userViewService
                .getStatisticalDataByUserId(customUtils
                        .getCurrentUser()
                        .getId());
        map.put("categories", categories);
        map.put("ds", dataStatistics);
        return map;
    }
}
