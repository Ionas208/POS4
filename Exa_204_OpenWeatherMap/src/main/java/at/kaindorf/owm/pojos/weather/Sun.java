/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.owm.pojos.weather;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
public class Sun {
    @XmlAttribute
    private String rise;
    @XmlAttribute
    private String set;
    
    public String getRiseFormatted(){
        String rise = this.rise.split("T")[1];
        String[] parts = rise.split(":");
        return parts[0]+":"+parts[1];
    }
    
    public String getSetFormatted(){
        String set = this.set.split("T")[1];
        String[] parts = set.split(":");
        return parts[0]+":"+parts[1];
    }
}
