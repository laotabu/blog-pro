package com.dgut.blog.controller;

import com.dgut.blog.dto.ResponseDTO;
import com.dgut.blog.entity.Article;
import com.dgut.blog.service.ArticleService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章栏目controller，只有管理员才能调用
 * @createDate: 2021/2/25
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    //private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseDTO addNewArticle(@RequestBody Article article) {
        System.out.println("新增文章" + article);
//        int result = articleService.addNewArticle(article);
//        if (result == 1) {
//            return new ResponseDTO("success", article.getId() + "");
//        } else {
//            return new ResponseDTO("error", article.getState() == 0 ? "文章保存失败!" : "文章发表失败!");
//        }
        return null;
    }

//    /**
//     * 上传图片
//     *
//     * @return 返回值为图片的地址
//     */
//    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
//    public ResponseDTO uploadImg(HttpServletRequest req, MultipartFile image) {
//        StringBuffer url = new StringBuffer();
//        String filePath = "/blogimg/" + sdf.format(new Date());
//        String imgFolderPath = req.getServletContext().getRealPath(filePath);
//        File imgFolder = new File(imgFolderPath);
//        if (!imgFolder.exists()) {
//            imgFolder.mkdirs();
//        }
//        url.append(req.getScheme())
//                .append("://")
//                .append(req.getServerName())
//                .append(":")
//                .append(req.getServerPort())
//                .append(req.getContextPath())
//                .append(filePath);
//        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
//        try {
//            IOUtils.write(image.getBytes(), new FileOutputStream(new File(imgFolder, imgName)));
//            url.append("/").append(imgName);
//            return new ResponseDTO("success", url.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new ResponseDTO("error", "上传失败!");
//    }
//
//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    public Map<String, Object> getArticleByState(@RequestParam(value = "state", defaultValue = "-1") Integer state, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count,String keywords) {
//        int totalCount = articleService.getArticleCountByState(state, Util.getCurrentUser().getId(),keywords);
//        List<Article> articles = articleService.getArticleByState(state, page, count,keywords);
//        Map<String, Object> map = new HashMap<>();
//        map.put("totalCount", totalCount);
//        map.put("articles", articles);
//        return map;
//    }
//
//    @RequestMapping(value = "/{aid}", method = RequestMethod.GET)
//    public Article getArticleById(@PathVariable Long aid) {
//        return articleService.getArticleById(aid);
//    }
//
//    @RequestMapping(value = "/dustbin", method = RequestMethod.PUT)
//    public ResponseDTO updateArticleState(Long[] aids, Integer state) {
//        if (articleService.updateArticleState(aids, state) == aids.length) {
//            return new ResponseDTO("success", "删除成功!");
//        }
//        return new ResponseDTO("error", "删除失败!");
//    }
//
//    @RequestMapping(value = "/restore", method = RequestMethod.PUT)
//    public ResponseDTO restoreArticle(Integer articleId) {
//        if (articleService.restoreArticle(articleId) == 1) {
//            return new ResponseDTO("success", "还原成功!");
//        }
//        return new ResponseDTO("error", "还原失败!");
//    }
//
//    @RequestMapping("/dataStatistics")
//    public Map<String,Object> dataStatistics() {
//        Map<String, Object> map = new HashMap<>();
//        List<String> categories = articleService.getCategories();
//        List<Integer> dataStatistics = articleService.getDataStatistics();
//        map.put("categories", categories);
//        map.put("ds", dataStatistics);
//        return map;
//    }
}
