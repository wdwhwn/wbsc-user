package com.jingzhun.wbsc.title.entity;

/**
 * Created by Administrator on 2019/6/15 0015.
 */
public class Msg {
    private String err;
    private String msg;

    @Override
    public String toString() {
        return "Msg{" +
                "err='" + err + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
