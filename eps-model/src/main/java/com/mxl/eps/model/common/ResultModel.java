package com.mxl.eps.model.common;

import lombok.Data;

/**
 * 通用返回数据-实体类
 * @author MXL
 * @date 2022/4/15
 **/
@Data
public class ResultModel {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;

    public ResultModel(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultModel ok() {
        return new ResultModel(200, "ok", null);
    }

    public static ResultModel okData(Object data) {
        return new ResultModel(200, "ok", data);
    }

    public static ResultModel failData(Object data) {
        return new ResultModel(500, "fail", data);
    }

    public static ResultModel failMsg(String msg) {
        return new ResultModel(500, msg, null);
    }
}
