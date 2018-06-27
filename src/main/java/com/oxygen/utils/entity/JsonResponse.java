package com.oxygen.utils.entity;

public class JsonResponse<T> {
    private Boolean error; //是否错误
    private String msg;  //信息
    private T data;

    public JsonResponse(){
        this.error = true;
        this.msg = "出错啦！！";
    }



    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
