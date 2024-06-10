package com.limyel.haoyuan.blog.main.service;

import com.limyel.haoyuan.blog.main.dao.TagDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagDao tagDao;

}
