/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 *
 * @author 10jon
 */
public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
    
    public LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        try{
            return LocalDate.parse(dc.readValue(jp, String.class), DTF);
        }catch(DateTimeParseException ex){
            return null;
        } 
    }
    
    
}
