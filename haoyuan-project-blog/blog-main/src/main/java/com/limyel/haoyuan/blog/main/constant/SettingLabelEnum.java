package com.limyel.haoyuan.blog.main.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SettingLabelEnum {
    BLOG_NAME("blog_name", "博客名"),
    BLOG_ABOUT("blog_about", "博客关于"),
    GITHUB_NAME("github_name", "github 名称"),
    GITHUB_TOKEN("github_token", "github token")
    ;

    private final String label;
    private final String name;

}
