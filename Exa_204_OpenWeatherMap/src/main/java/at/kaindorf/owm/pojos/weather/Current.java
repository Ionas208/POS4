/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.owm.pojos.weather;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author 10jon
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "current")
@XmlAccessorType (XmlAccessType.FIELD)
public class Current {
    private City city;
    private Temperature temperature;
    private Humidity humidity;
    private Pressure pressure;
    private Wind wind;
    private Clouds clouds;
    private Weather weather;
}
