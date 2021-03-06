package com.jazz.pipeline;

/**
 * 基础阀门，处理逻辑仅仅是简单的将传入的字符串中”aa”替换成”bb”
 * Created by XUJING592 on 2018/1/25.
 */
public class BasicValve implements Valve {
    protected Valve next = null;
    public Valve getNext() {
        return next;
    }
    public void invoke(String handling) {
        handling=handling.replaceAll("aa", "bb");
        System.out.println("基础阀门处理完后：" + handling);
    }
    public void setNext(Valve valve) {
        this.next = valve;
    }
}