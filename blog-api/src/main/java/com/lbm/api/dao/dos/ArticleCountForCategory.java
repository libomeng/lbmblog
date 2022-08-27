package com.lbm.api.dao.dos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class ArticleCountForCategory {
    @JsonSerialize(using = ToStringSerializer.class)
    private String id;
    private String categoryName;
    private Integer count;

}
