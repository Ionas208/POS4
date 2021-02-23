/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.plf_uebung.io.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author 10jon
 */
public class LocalDateSerializer extends StdSerializer<LocalDate>{

    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    public LocalDateSerializer(){
        super(LocalDate.class);
    }
    
    @Override
    public void serialize(LocalDate t, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeString(DTF.format(t));
    }
    
}
