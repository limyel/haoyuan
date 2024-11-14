package com.limyel.blog.controller;

import com.limyel.blog.model.dto.ArticleListDTO;
import com.limyel.blog.service.ArticleService;
import com.limyel.blog.service.CountDaysService;
import com.limyel.blog.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class BlogPageController {

    private final ArticleService articleService;

    private final TagService tagService;

    private final CountDaysService countDaysService;

    @GetMapping("/")
    public ModelAndView index(ArticleListDTO dto, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("request", request);
        mv.addObject("articleObj", articleService.list(dto));
        mv.addObject("tagList", tagService.list());
        mv.addObject("yearList", articleService.listYear());
        return mv;
    }

    @GetMapping("/count-days")
    public ModelAndView countDays(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("countdays");
        mv.addObject("request", request);

        return mv;
    }

    @GetMapping("/about")
    public ModelAndView about(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("about");
        mv.addObject("request", request);
        return mv;
    }

    @GetMapping("/article/{slug}")
    public ModelAndView getArticle(@PathVariable("slug") String slug, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("article");
        mv.addObject("request", request);
        mv.addObject("article", articleService.get(slug));
        return mv;
    }

}
