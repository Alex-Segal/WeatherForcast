package com.weather.util;

import com.pi4j.temperature.*;

public class DegreeConvertor {


    public double convertKelToFar(Double temp){
        return TemperatureConversion.convert(TemperatureScale.KELVIN, TemperatureScale.FARENHEIT, temp);
    }


    // many other conversions can be done per need


}
