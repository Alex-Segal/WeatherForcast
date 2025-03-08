package com.weather.DAO;

public class Forcast {
    private double temp;
    private double highTmp;
    private double lowTmp;

    private String location;

    public double getTemp() {
        return temp;
    }

    public double getHighTmp() {
        return highTmp;
    }

    public double getLowTmp() {
        return lowTmp;
    }

    public String getLocation() {
        return location;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setHighTmp(double highTmp) {
        this.highTmp = highTmp;
    }

    public void setLowTmp(double lowTmp) {
        this.lowTmp = lowTmp;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
