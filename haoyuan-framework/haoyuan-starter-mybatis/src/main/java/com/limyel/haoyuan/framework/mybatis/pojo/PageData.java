package com.limyel.haoyuan.framework.mybatis.pojo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long pages;

    private Long total;

    private Long current;

    private List<T> list;

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
