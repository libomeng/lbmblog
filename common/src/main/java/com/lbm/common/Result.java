package com.lbm.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Boolean success;
    private Integer code;
    private String message;
    private Object data;

    public static Result success(Object data) {
        return new Result(true,200,"success",data);
    }
    public static Result success(String msg){
        return new Result(true,200,msg,null);
    }
    public static Result success(String msg,Object data){
        return new Result(true,200,msg,data);
    }

    public static Result fail(Integer code, String msg) {
        return new Result(false,code,msg,null);
    }
    public static Result fail( String msg) {
        return new Result(false,-999,msg,null);
    }
}
