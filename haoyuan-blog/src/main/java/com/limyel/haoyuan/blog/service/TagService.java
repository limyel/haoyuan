package com.limyel.haoyuan.blog.service;

import com.limyel.haoyuan.blog.dao.PostDao;
import com.limyel.haoyuan.blog.dao.TagDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagDao tagDao;

}
