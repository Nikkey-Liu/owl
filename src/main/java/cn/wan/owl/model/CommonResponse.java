package cn.wan.owl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {

    private String msg;

    private Object data;

    private int code;



    public static CommonResponse success(Object data) {
        return new CommonResponse("success", data, 0);
    }

    public static CommonResponse success() {
        return new CommonResponse("success", "", 0);
    }

    public static Object failed(String msg) {
        return new CommonResponse(msg, "", -1);
    }

    public static Object failed() {
        return new CommonResponse("", "", -1);
    }
}
