package com.lbm.api.dao.dos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class TagCount {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String tagName;
    private Integer count;
}
