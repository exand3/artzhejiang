package cn.com.zhejiangart.util;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;
    private String msg;
    private Object data;


    public static <T> Result<T> error(Integer code, String msg)
    {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> success(Object data)
    {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("获取成功");
        result.setData(data);
        return result;
    }


}
