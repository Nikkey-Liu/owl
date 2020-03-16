package cn.wan.owl.exception;

import com.alibaba.fastjson.JSONObject;

public interface IError {

    int getCode();

    String getMessage();

    default String getJsonStr() {
        JSONObject obj = new JSONObject();
        obj.put("code", getCode());
        obj.put("msg", getMessage());
        return obj.toJSONString();
    }

}
