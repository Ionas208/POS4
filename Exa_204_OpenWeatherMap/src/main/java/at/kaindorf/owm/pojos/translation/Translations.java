/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.owm.pojos.translation;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author 10jon
 */
@XmlRootElement(name = "translations")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Translations {
    @XmlElement(name = "translation")
    private List<Translation> translations = null;
}
