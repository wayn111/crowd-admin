package com.wayn.commom.util;

import java.util.HashMap;
import java.util.Map;

public class Response {

    private int code;
    private String msg;
    private Map<String, Object> map = new HashMap<String, Object>();

    public static Response success() {
        Response response = new Response();
        response.code = 100;
        response.msg = "请求成功";
        return response;
    }

    public static Response success(String msg) {
        Response response = new Response();
        response.code = 100;
        response.msg = msg;
        return response;
    }

    public static Response result(boolean flag, String successTip) {
        return flag ? success(successTip) : error();
    }

    public static Response error() {
        Response response = new Response();
        response.code = 200;
        response.msg = "请求失败";
        return response;
    }

    public static Response error(String msg) {
        Response response = new Response();
        response.code = 200;
        response.msg = msg;
        return response;
    }

    public Response add(String key, Object value) {
        map.put(key, value);
        return this;
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

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", map=" + map +
                '}';
    }
}
