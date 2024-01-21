package ${project}.module.${module}.${package}.controller;

import ${project}.framework.mybatis.pojo.PageData;
import ${project}.framework.web.pojo.Result;
import ${project}.module.${module}.${package}.convert.${beanName}Convert;
import ${project}.module.${module}.${package}.dto.${beanName}DTO;
import ${project}.module.${module}.${package}.dto.${beanName}FilterDTO;
import ${project}.module.${module}.${package}.entity.${beanName}Entity;
import ${project}.module.${module}.${package}.service.${beanName}Service;
import ${project}.module.${module}.${package}.vo.${beanName}VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("${route}")
@Validated
public class ${beanName}Controller {

    @Autowired
    private ${beanName}Service ${beanInstanceName}Service;

    @PostMapping
    public Result<Long> create(@RequestBody ${beanName}DTO dto) {
        Long id = ${beanInstanceName}Service.create(dto);
        return Result.ok(id);
    }

    @PutMapping
    public Result<?> update(@RequestBody ${beanName}DTO dto) {
        ${beanInstanceName}Service.update(dto);
        return new Result<>();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable("id") Long id) {
        ${beanInstanceName}Service.delete(id);
        return new Result<>();
    }

    @GetMapping("/{id}")
    public Result<${beanName}VO> get(@PathVariable("id") Long id) {
        ${beanName}Entity ${beanInstanceName} = ${beanInstanceName}Service.get(id);
        return Result.ok(${beanName}Convert.INSTANCE.toVO(${beanInstanceName}));
    }

    @GetMapping
    public Result<PageData<${beanName}VO>> getPage(${beanName}FilterDTO dto) {
        PageData<${beanName}Entity> page = ${beanInstanceName}Service.getPage(dto);
        return Result.ok(new PageData<>(page, ${beanName}Convert.INSTANCE.toListVO(page.getList())));
    }
}
