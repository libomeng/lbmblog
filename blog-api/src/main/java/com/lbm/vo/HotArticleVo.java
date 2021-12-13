package com.lbm.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class HotArticleVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
    private String title;
}
