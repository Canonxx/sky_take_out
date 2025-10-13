package com.hubu.result;/*
 * @Auther:long
 * @Date:2025/7/5
 * @Description:返回前端结果类
 * @VERSON:1.8
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    private  Integer code;//1表示成功 0和其他数字表示失败
    private  String msg;//错误信息
    private  T data;//数据
    //返回前端的错误信息结果
    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<>();
        result.code = 0;
        result.msg = msg;
        return result;
    }
    // 返回成功的响应码
    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.code = 1;
        result.data = data;
        return result;
    }
    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.code=1;
        return result;
    }
}
