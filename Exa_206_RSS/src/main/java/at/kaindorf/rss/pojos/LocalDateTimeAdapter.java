/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.rss.pojos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author 10jon
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime>{

    private DateTimeFormatter DTF = DateTimeFormatter.ofPattern("E, dd MMM yyyy hh:mm:ss");
    
    @Override
    public LocalDateTime unmarshal(String vt) throws Exception {
        return LocalDateTime.parse(vt, DTF);
    }

    @Override
    public String marshal(LocalDateTime bt) throws Exception {
        return DTF.format(bt);
    }
    
}
