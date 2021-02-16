/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.owm.pojos.translation;

import at.kaindorf.owm.beans.Language;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
@XmlRootElement(name = "translation")
@XmlAccessorType (XmlAccessType.FIELD)
public class Translation {
    @XmlAttribute
    private String key;
    private String en;
    private String de;
    
    public String getValue(Language l){
        if(l == Language.DE){
            return de;
        }
        else if(l == Language.EN){
            return en;
        }
        return null;
    }
}
