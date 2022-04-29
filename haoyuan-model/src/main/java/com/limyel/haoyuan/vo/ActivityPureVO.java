package com.limyel.haoyuan.vo;

import com.limyel.haoyuan.entity.Activity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ActivityPureVO {

    private Long id;

    private String title;

    private String entranceImg;

    private String remark;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public ActivityPureVO(Activity activity) {
        BeanUtils.copyProperties(activity, this);
    }

}
