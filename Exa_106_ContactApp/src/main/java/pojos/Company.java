/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import beans.Stockmarket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author 10jon
 */
@Data
@RequiredArgsConstructor
@EqualsAndHashCode
public class Company implements Comparable<Company>{
    @NonNull
    private String name;
    @NonNull
    private Stockmarket stockmarket;
    
    @Setter(AccessLevel.NONE)
    private List<Contact> contacts = new ArrayList<>();
    
    private static Set<Company> companies = new HashSet<>();

    @Override
    public int compareTo(Company o) {
        return this.name.compareTo(o.getName());
    }
    
    public static Company getCorrectCompany(Company c){
        if(companies.contains(c)){
            for (Company company : companies) {
                if(company.equals(c)){
                    return company;
                }
            }
        }
        else{
            companies.add(c);
            return c;
        }
        return null;
    }
    
    public static Set<Company> getCompanies(){
        return companies;
    }
    
    public void addContact(Contact c){
        this.contacts.add(c);
    }
}
