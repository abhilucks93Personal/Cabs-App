package com.abhi.taxiapp.profile.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by abhi on 21/04/17.
 */

public class SignUpResponseMain implements Serializable {

    private String statusCode;

    private String statusMsg;

    private ArrayList<SignUpResponseData> data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public ArrayList<SignUpResponseData> getData() {
        return data;
    }

    public void setData(ArrayList<SignUpResponseData> data) {
        this.data = data;
    }
}
