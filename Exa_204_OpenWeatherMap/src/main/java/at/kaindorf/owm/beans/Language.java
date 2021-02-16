/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.owm.beans;

/**
 *
 * @author 10jon
 */
public enum Language {
    DE("Deutsch"),
    EN("English");
    
    private Language(String langFull){
        this.langFull = langFull;
    }
    
    private final String langFull;

    public String getLangFull() {
        return langFull;
    }
}
