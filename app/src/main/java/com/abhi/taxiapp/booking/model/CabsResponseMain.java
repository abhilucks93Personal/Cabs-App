package com.abhi.taxiapp.booking.model;

import java.util.ArrayList;

/**
 * Created by abhi on 15/05/17.
 */

public class CabsResponseMain {

    private String statusCode;

    private String statusMsg;

    private ArrayList<CabsResponseData> data;

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

    public ArrayList<CabsResponseData> getData() {
        return data;
    }

    public void setData(ArrayList<CabsResponseData> data) {
        this.data = data;
    }
}
