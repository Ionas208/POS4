/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author 10jon
 */
public enum Language {
    
   EN("EN","English"),
   DE("DE","Deutsch");
            
            
   private String language_code; 
   private String language_name;

    private Language(String language_code, String language_name) {
        this.language_code = language_code;
        this.language_name = language_name;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public String getLanguage_name() {
        return language_name;
    }

    
   
}