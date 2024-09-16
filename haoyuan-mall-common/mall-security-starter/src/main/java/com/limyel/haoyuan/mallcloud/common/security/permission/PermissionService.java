package com.limyel.haoyuan.mallcloud.common.security.permission;

import com.limyel.haoyuan.common.core.util.JSONUtil;
import com.limyel.haoyuan.mallcloud.common.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.Set;

@Service("pms")
@RequiredArgsConstructor
public class PermissionService {

    private final StringRedisTemplate redisTemplate;

    public boolean hasPermission(String perm) {
        if (!StringUtils.hasText(perm)) {
            return false;
        }

        Long sysUserId = SecurityUtil.getSysUserId();
        Set<String> perms = JSONUtil.parseObject(redisTemplate.opsForValue().get("AUTH:SYS_USER_PERMS:" + sysUserId), Set.class);

        if (CollectionUtils.isEmpty(perms)) {
            return false;
        }
        return perms.stream()
                .anyMatch(item -> PatternMatchUtils.simpleMatch(perm, item));
    }

    public boolean isMemberAuthenticated() {
        Optional<Long> memberUserId = SecurityUtil.getMemberUserId();
        return memberUserId.isPresent();
    }

}
