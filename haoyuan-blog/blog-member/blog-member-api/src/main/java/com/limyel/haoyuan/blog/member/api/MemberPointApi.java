package com.limyel.haoyuan.blog.member.api;

import com.limyel.haoyuan.blog.member.dto.point.PointChangeDTO;

public interface MemberPointApi {

    /**
     * 变动用户积分
     * @param dto
     */
    void changePoint(PointChangeDTO dto);

}
