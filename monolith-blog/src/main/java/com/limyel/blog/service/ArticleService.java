package com.limyel.blog.service;

import com.limyel.blog.dao.ArticleDao;
import com.limyel.blog.model.dto.ArticleDTO;
import com.limyel.blog.model.dto.ArticleListDTO;
import com.limyel.blog.model.entity.ArticleEntity;
import com.limyel.blog.model.entity.ArticleTagEntity;
import com.limyel.blog.model.entity.TagEntity;
import com.limyel.blog.model.vo.ArticleListVO;
import com.limyel.blog.model.vo.ArticleVO;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.MutableDataSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleDao articleDao;

    private final TagService tagService;

    private final ArticleTagService articleTagService;

    public void create(ArticleDTO dto) {
        ArticleEntity article = new ArticleEntity();
        BeanUtils.copyProperties(dto, article);
        articleDao.save(article);

        articleTagService.deleteByArticle(article.getId());
        articleTagService.create(article.getId(), dto.getTags());
    }

    public void update(ArticleDTO dto) {
        Optional<ArticleEntity> optional = articleDao.findById(dto.getId());
        ArticleEntity article = optional.orElseThrow(() -> new RuntimeException("文章不存在"));
        BeanUtils.copyProperties(dto, article);
        articleDao.save(article);

        articleTagService.deleteByArticle(article.getId());
        articleTagService.create(article.getId(), dto.getTags());
    }

    public ArticleVO get(String slug) {
        ArticleEntity article = articleDao.findBySlug(slug);
        if (Objects.isNull(article)) {
            throw new RuntimeException("文章不存在");
        }

        ArticleVO result = new ArticleVO();
        result.setTitle(article.getTitle());

        MutableDataSet options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Document document = parser.parse(article.getContent());
        result.setContent(renderer.render(document));

        result.setTags(tagService.listByArticle(article.getId()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        result.setCreateTime(formatter.format(article.getCreateTime()));
        result.setUpdateTime(formatter.format(article.getUpdateTime()));

        return result;
    }

    public ArticleDTO get(Long id) {
        return articleDao.findById(id).map(article -> {
            ArticleDTO dto = new ArticleDTO();
            BeanUtils.copyProperties(article, dto);
            dto.setTags(articleTagService.listByArticle(article.getId()).stream()
                    .map(ArticleTagEntity::getTagId)
                    .toList());
            return dto;
        }).orElseThrow(() -> new RuntimeException("文章不存在"));
    }

    public ArticleListVO list(ArticleListDTO dto) {
        LocalDateTime startTime = LocalDateTime.of(dto.getYear(), 1, 1, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(dto.getYear(), 12, 31, 23, 59);

        List<ArticleEntity> articles = null;
        ArticleListVO result = new ArticleListVO();
        result.setYear(dto.getYear());
        if (StringUtils.hasText(dto.getTag())) {
            TagEntity tag = tagService.get(dto.getTag());
            result.setTag(tag.getName());
            List<Long> articleIds = articleTagService.listArticleId(tag.getId());
            articles = articleDao.findByCreateTimeBetweenAndIdInOrderByCreateTimeDesc(startTime, endTime, articleIds);
        } else {
            articles = articleDao.findByCreateTimeBetweenOrderByCreateTimeDesc(startTime, endTime);
        }

        Map<Integer, List<ArticleListVO.Article>> map = new HashMap<>();
        articles.forEach(item -> {
            Integer month = item.getCreateTime().getMonthValue();
            List<ArticleListVO.Article> list = map.computeIfAbsent(month, k -> new ArrayList<>());

            ArticleListVO.Article article = new ArticleListVO.Article();
            article.setTitle(item.getTitle());
            article.setSlug(item.getSlug());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
            article.setCreateTime(formatter.format(item.getCreateTime()));

            list.add(article);
        });

        List<ArticleListVO.ArticleListItem> list = new ArrayList<>();
        for (Map.Entry<Integer, List<ArticleListVO.Article>> entry : map.entrySet()) {
            ArticleListVO.ArticleListItem articleListItem = new ArticleListVO.ArticleListItem();
            articleListItem.setMonthNum(entry.getKey());
            articleListItem.setMonth(getMonth(entry.getKey()));
            articleListItem.setArticles(entry.getValue());
            list.add(articleListItem);
        }
        list = list.stream()
                .sorted(Comparator.comparing(ArticleListVO.ArticleListItem::getMonthNum).reversed())
                .toList();
        result.setList(list);

        return result;
    }

    public PageData<ArticleEntity> page(int pageNum, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNum, pageSize);
        return articleDao.findAll(pageable);
    }

    public List<Integer> listYear() {
        return articleDao.findYearByCreateTime();
    }

    private String getMonth(Integer monthValue) {
        return switch (monthValue) {
            case 1 -> "一月";
            case 2 -> "二月";
            case 3 -> "三月";
            case 4 -> "四月";
            case 5 -> "五月";
            case 6 -> "六月";
            case 7 -> "七月";
            case 8 -> "八月";
            case 9 -> "九月";
            case 10 -> "十月";
            case 11 -> "十一月";
            case 12 -> "十二月";
            default -> null;
        };
    }

}
