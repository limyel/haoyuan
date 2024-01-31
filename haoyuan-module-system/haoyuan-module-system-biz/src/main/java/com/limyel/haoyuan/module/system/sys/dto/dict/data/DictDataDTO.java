package com.limyel.haoyuan.module.system.sys.dto.dict.data;

import lombok.Data;

@Data
public class DictDataDTO {

    private Long id;

    private Integer sort;

    private String label;

    private String value;

    private String type;

    private Integer status;

    private String colorType;

    private String cssClass;

    private String remark;

}
