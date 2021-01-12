/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author 10jon
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rating {
    @JsonProperty(value = "Source")
    private String source;
    
    @JsonProperty(value = "Value")
    private String value;
}
