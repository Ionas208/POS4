/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import beans.Gender;
import beans.Stockmarket;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import pojos.Company;
import pojos.Contact;

/**
 *
 * @author 10jon
 */
public class ContactDeserializer extends JsonDeserializer<Contact>{

    @Override
    public Contact deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        int id = (Integer) ((IntNode)node.get("id")).numberValue();
        String firstname = node.get("firstname").asText();
        String lastname = node.get("lastname").asText();
        List<String> email = new ArrayList();
        ((ArrayNode)node.get("email"))
                    .forEach(n -> email.add(n.textValue()));
        Gender gender = Gender.valueOf((node.get("gender").asText().charAt(0)+"").toUpperCase());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateOfBirth = LocalDate.parse(node.get("dateOfBirth").textValue(), dtf);
        
        JsonNode companyNode = node.get("company");
        String name = companyNode.get("name").textValue();
        Stockmarket stockmarket = Stockmarket.valueOf(companyNode.get("stockmarket").textValue().toUpperCase());
        Company company = new Company(name, stockmarket);
        company = Company.getCorrectCompany(company);
        
        return new Contact(id, firstname, lastname, email, gender, dateOfBirth, company);
    }
    
}
