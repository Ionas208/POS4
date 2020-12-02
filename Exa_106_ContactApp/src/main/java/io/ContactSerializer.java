/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import pojos.Contact;

/**
 *
 * @author 10jon
 */
public class ContactSerializer extends JsonSerializer<Contact>{

    @Override
    public void serialize(Contact c, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeStartObject();
        jg.writeNumberField("id", c.getId());
        jg.writeStringField("firstname", c.getFirstname());
        jg.writeStringField("lastname", c.getLastname());
        
        jg.writeFieldName("email");
        jg.writeStartArray();
        for (String email: c.getEmail()) {
            jg.writeString(email);
        }
        jg.writeEndArray();
        
        jg.writeStringField("gender", c.getGender().getGender());
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        jg.writeStringField("dateOfBirth", dtf.format(c.getDateOfBirth()));
        
        jg.writeFieldName("company");
        jg.writeStartObject();
        jg.writeStringField("name", c.getCompany().getName());
        jg.writeStringField("stockmarket", c.getCompany().getStockmarket().name());
        jg.writeEndObject();
        
        jg.writeEndObject();
    }
    
}
