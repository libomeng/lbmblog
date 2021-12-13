package com.lbm.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class DetailTagVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String tagName;

    private String avatar;
}
