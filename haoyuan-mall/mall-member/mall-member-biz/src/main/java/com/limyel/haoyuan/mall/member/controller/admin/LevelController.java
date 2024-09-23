package com.limyel.haoyuan.mall.member.controller.admin;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.common.core.validator.group.Update;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.mall.common.member.dto.level.LevelDTO;
import com.limyel.haoyuan.mall.common.member.dto.level.LevelPageDTO;
import com.limyel.haoyuan.mall.common.member.vo.level.LevelPageVO;
import com.limyel.haoyuan.mall.member.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("adminLevelControler")
@RequestMapping("/level")
@RequiredArgsConstructor
public class LevelController {

    private final LevelService levelService;

    @PostMapping("/create")
    public R<?> create(@Validated(Create.class) @RequestBody LevelDTO dto) {
        levelService.create(dto);
        return R.ok();
    }

    @GetMapping("/delete/{ids}")
    public R<?> delete(@PathVariable("ids") String ids) {
        // todo 封装
        List<Long> idList = new ArrayList<>();
        for (String idStr : ids.split(",")) {
            if (!StringUtils.hasText(idStr) || !idStr.matches("\\d+")) {
                throw new ServiceException("参数异常");
            }
            idList.add(Long.parseLong(idStr));
        }
        levelService.delete(idList);
        return R.ok();
    }

    @PostMapping("/update")
    public R<?> update(@Validated(Update.class) @RequestBody LevelDTO dto) {
        levelService.update(dto);
        return R.ok();
    }

    @GetMapping("/get/page")
    public R<PageData<LevelPageVO>> getPage(LevelPageDTO dto) {
        PageData<LevelPageVO> result = levelService.getPage(dto);
        return R.ok(result);
    }

}
