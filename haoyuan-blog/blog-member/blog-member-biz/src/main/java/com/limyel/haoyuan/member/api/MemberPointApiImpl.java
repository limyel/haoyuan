package com.limyel.haoyuan.member.api;

import com.limyel.haoyuan.blog.member.api.MemberPointApi;
import com.limyel.haoyuan.blog.member.dto.point.PointChangeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberPointApiImpl implements MemberPointApi {

    @Override
    public void changePoint(PointChangeDTO dto) {

    }

}
