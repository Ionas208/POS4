/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.owm.bl;

import at.kaindorf.owm.beans.Language;
import at.kaindorf.owm.pojos.weather.Current;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import javax.xml.bind.UnmarshalException;

/**
 *
 * @author 10jon
 */
public class RequestHandler {
    
    private static String apiKey = "632bb73d2e356b713dc1099395be92b1";
    
    public static Current getCurrentWeather(String cityName, Language lang) throws MalformedURLException, DataBindingException{
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&appid="+apiKey+"&mode=xml&units=metric&lang="+lang.name());
        return JAXB.unmarshal(url, Current.class);
    }
}
