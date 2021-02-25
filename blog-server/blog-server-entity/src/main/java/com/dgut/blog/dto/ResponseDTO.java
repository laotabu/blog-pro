package com.dgut.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 响应参数实体类
 * @createDate: 2021/2/25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private String status;
    private String msg;

}
