package com.github.rpc.remoting.socket;

import java.io.Serializable;

/**
 * @author sunzy
 * @date 2023/8/2 10:17
 */
public class Message implements Serializable {
    private String msg;



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
