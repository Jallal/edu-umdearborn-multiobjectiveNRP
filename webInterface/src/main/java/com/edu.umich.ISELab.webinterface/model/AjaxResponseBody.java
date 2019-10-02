
package com.edu.umich.ISELab.webinterface.model;

import java.util.List;

public class AjaxResponseBody {

    /*String msg;
    List<User> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }*/


    String msg;
    List<String[][]> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String[][]> getResult() {
        return result;
    }

    public void setResult(List<String[][]> result) {
        this.result = result;
    }

}
