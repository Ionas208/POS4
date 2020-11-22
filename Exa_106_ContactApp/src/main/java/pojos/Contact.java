/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import beans.Gender;
import beans.Stockmarket;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.ContactDeserializer;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
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
@JsonDeserialize(using = ContactDeserializer.class)
public class Contact {
    private int id;
    private String firstname;
    private String lastname;
    private List<String> email;
    private Gender gender;
    private LocalDate dateOfBirth;
    private Company company;   
    
    
    public Integer getAge(){
        return (int)ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
    }
}
