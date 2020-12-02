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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.ContactDeserializer;
import io.ContactSerializer;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author 10jon
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = ContactDeserializer.class)
@JsonSerialize(using = ContactSerializer.class)
public class Contact {
    @NonNull
    private int id;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @NonNull
    private List<String> email;
    @NonNull
    private Gender gender;
    @NonNull
    private LocalDate dateOfBirth;
    @NonNull
    private Company company;
    @JsonIgnore
    private boolean favourite = false;
    
    public Integer getAge(){
        return (int)ChronoUnit.DAYS.between(dateOfBirth, LocalDate.now());
    }
}
