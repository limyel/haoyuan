package com.limyel.haoyuan.common.mybatis.pojo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页结果
 * @param <T>
 */
@Data
@NoArgsConstructor
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 当前页
     */
    private Long current;

    /**
     * 每页记录
     */
    private List<T> list = new ArrayList<>();

    public PageData(IPage<T> page) {
        this.pages = page.getPages();
        this.total = page.getTotal();
        this.current = page.getCurrent();
        this.list = page.getRecords();
    }

    public PageData(PageData<?> pageData, List<T> list) {
        this.pages = pageData.getPages();
        this.total = pageData.getTotal();
        this.current = pageData.getCurrent();
        this.list = list;
    }

    public PageData(IPage<?> page, List<T> list) {
        this.pages = page.getPages();
        this.total = page.getTotal();
        this.current = page.getCurrent();
        this.list = list;
    }

}
