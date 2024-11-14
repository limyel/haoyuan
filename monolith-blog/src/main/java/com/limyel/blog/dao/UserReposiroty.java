package com.limyel.blog.dao;

import com.limyel.blog.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposiroty extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

}
