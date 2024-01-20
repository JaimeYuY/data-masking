package com.ocean.angel.tool.common;

import com.ocean.angel.tool.constatnt.ResultCode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultPage<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 数据总数
    private Integer total;

    // 响应数据
    private List<T> rows;

    // 响应码
    private int code;

    // 提示信息
    private String msg;

    public ResultPage() {
    }

    public ResultPage(List<T> list, int total) {
        this.rows = list;
        this.total = total;
    }

    public ResultPage(Integer total, List<T> rows, int code, String msg) {
        this.total = total;
        this.rows = rows;
        this.code = code;
        this.msg = msg;
    }

    public static <T> ResultPage<T> success(List<T> list, Integer total) {
        return new ResultPage(total, list, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static <T> ResultPage<T> error(int code, String msg) {
        return new ResultPage(0, new ArrayList(), code, msg);
    }

    public static <T> ResultPage<T> error() {
        return new ResultPage(0, new ArrayList(), ResultCode.SERVICE_INTERNAL_ERROR.getCode(), ResultCode.SERVICE_INTERNAL_ERROR.getMsg());
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
