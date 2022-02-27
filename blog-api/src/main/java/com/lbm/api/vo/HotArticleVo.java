package com.lbm.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class HotArticleVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private String id;
    private String title;
}
