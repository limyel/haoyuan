package com.limyel.haoyuan.blog.content.event.subscriber;

import com.limyel.haoyuan.blog.content.dao.PostDao;
import com.limyel.haoyuan.blog.content.event.PostViewEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostViewSubscriber implements ApplicationListener<PostViewEvent> {

    private final PostDao postDao;

    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(PostViewEvent event) {
        Long postId = event.getPostId();

        postDao.increaseViewNum(postId);
    }
}
