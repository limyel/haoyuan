package com.limyel.haoyuan.api.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 标签 前端控制器
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Tag(name = "Tag", description = "标签接口")
@RestController
@RequestMapping("/tag")
public class TagController {

}

