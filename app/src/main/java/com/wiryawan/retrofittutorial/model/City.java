package com.wiryawan.retrofittutorial.model;

import java.util.List;

/**
 * Created by wiryawan on 1/13/17.
 */

public class City {

    private String status;
    private String message;
    private List<CityData> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CityData> getData() {
        return data;
    }

    public void setData(List<CityData> data) {
        this.data = data;
    }
}
