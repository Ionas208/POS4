/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Map;

/**
 *
 * @author 10jon
 */
public class Pizza {
    private String name;
    private Map<Language,String> description;
    private double price;
    private String img_url;

    public Pizza() {
    }

    public Pizza(String name, Map<Language, String> description, double price, String img_url) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Language, String> getDescription() {
        return description;
    }

    public void setDescription(Map<Language, String> description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    

    

    

    
    
    
}
