package com.limyel.haoyuan.blog.member.api;

import com.limyel.haoyuan.blog.member.dto.point.PointChangeDTO;
import com.limyel.haoyuan.blog.member.dto.point.PointCreateDTO;

public interface MemberPointApi {

    int create(PointCreateDTO dto);

    /**
     * 增加用户积分
     * @param dto
     */
    void addPoint(PointChangeDTO dto);

    void reducePoint(PointChangeDTO dto);

}
