package com.limyel.haoyuan.dao;

import com.limyel.haoyuan.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

    List<Banner> findBannersByNameIn(List<String> names);

}
