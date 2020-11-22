/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import beans.Stockmarket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author 10jon
 */
@Data
@AllArgsConstructor
public class Company implements Comparable<Company>{
    private String name;
    private Stockmarket stockmarket;

    @Override
    public int compareTo(Company o) {
        return this.name.compareTo(o.getName());
    }
}
