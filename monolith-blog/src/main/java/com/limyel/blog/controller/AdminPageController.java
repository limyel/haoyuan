package com.limyel.blog.controller;

import com.limyel.blog.model.dto.ArticleDTO;
import com.limyel.blog.model.dto.LoginDTO;
import com.limyel.blog.model.dto.TagDTO;
import com.limyel.blog.model.entity.UserEntity;
import com.limyel.blog.service.ArticleService;
import com.limyel.blog.service.TagService;
import com.limyel.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminPageController {

    private final UserService userService;

    private final ArticleService articleService;

    private final TagService tagService;

    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("admin/login");
        mv.addObject("request", request);
        return mv;
    }

    @PostMapping("/do-login")
    public String doLogin(@ModelAttribute LoginDTO dto, HttpSession session) {
        UserEntity user = userService.doLogin(dto);
        if (user == null) {
            log.info("用户名或密码错误");
            return "redirect:/admin/login";
        }
        session.setAttribute("user", user);
        session.setAttribute("userId", user.getId());
        return "redirect:/admin/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }

    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("admin/index");
        mv.addObject("request", request);
        return mv;
    }

    @GetMapping("/article")
    public ModelAndView articlePage(@RequestParam(defaultValue = "0") int pageNum,
                                    @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("admin/article");
        mv.addObject("request", request);
        mv.addObject("articlePage", articleService.page(pageNum, pageSize));
        return mv;
    }

    @GetMapping({"/article/edit/{id}", "/article/edit"})
    public ModelAndView articleEdit(@PathVariable(required = false) Long id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("admin/article_edit");
        mv.addObject("request", request);
        mv.addObject("tags", tagService.listAll());
        if (id != null) {
            mv.addObject("article", articleService.get(id));
        } else {
            mv.addObject("article", new ArticleDTO());
        }
        return mv;
    }

    @PostMapping("/article/do-edit")
    public String articleDoEdit(@ModelAttribute ArticleDTO dto) {
        if (dto.getId() == null) {
            articleService.create(dto);
        } else {
            articleService.update(dto);
        }
        return "redirect:/admin/article";
    }

    @GetMapping("/tag")
    public ModelAndView tagPage(@RequestParam(defaultValue = "0") int pageNum,
                                    @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("admin/tag");
        mv.addObject("request", request);
        mv.addObject("tagPage", tagService.page(pageNum, pageSize));
        return mv;
    }

    @GetMapping({"/tag/edit/{id}", "/tag/edit"})
    public ModelAndView tagEdit(@PathVariable(required = false) Long id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("admin/tag_edit");
        mv.addObject("request", request);
        if (id != null) {
            mv.addObject("tag", tagService.get(id));
        } else {
            mv.addObject("tag", new TagDTO());
        }
        return mv;
    }

    @PostMapping("/tag/do-edit")
    public String tagDoEdit(@ModelAttribute TagDTO dto) {
        if (dto.getId() == null) {
            tagService.create(dto);
        } else {
            tagService.update(dto);
        }
        return "redirect:/admin/tag";
    }


}
