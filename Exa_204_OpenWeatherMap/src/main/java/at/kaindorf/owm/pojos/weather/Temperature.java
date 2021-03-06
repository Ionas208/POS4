/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.owm.pojos.weather;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
@XmlAccessorType(XmlAccessType.FIELD)
public class Temperature {
    @XmlAttribute
    private String value;
    @XmlAttribute
    private String min;
    @XmlAttribute
    private String max;
    @XmlAttribute
    private String unit;
    
    public long getRoundedTemperature(){
        return Math.round(Double.parseDouble(value));
    }
}
