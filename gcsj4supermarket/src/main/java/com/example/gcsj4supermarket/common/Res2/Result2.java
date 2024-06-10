package com.example.gcsj4supermarket.common.Res2;

import lombok.Data;

@Data
public class Result2 {

    private int code;//编码 200/400
    private String msg;//成功/失败
    private Long total;//总记录数
    private Object data;//数据

    public static Result2 fail(){
        return result(400,"失败",0L,null);
    }

    public static Result2 suc(){
        return result(200,"成功",0L,null);
    }

    public static Result2 suc(Object data){
        return result(200,"成功",0L,data);
    }

    public static Result2 suc(Object data, Long total){
        return result(200,"成功",total,data);
    }

    private static Result2 result(int code, String msg, Long total, Object data){
        Result2 res = new Result2();
        res.setData(data);
        res.setMsg(msg);
        res.setCode(code);
        res.setTotal(total);
        return res;
    }

}
