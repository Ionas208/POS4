/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 *
 * @author 10jon
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private String brand;
    private String type;
    @JsonIgnore
    private double weight;
    
    public static void main(String[] args) {
        try {
            Car c1 = new Car("VW", "Polo", 1234);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(c1);
            Car c1FromJson = mapper.readValue(json, Car.class); //Car[].class for Array
            System.out.println(c1FromJson);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
