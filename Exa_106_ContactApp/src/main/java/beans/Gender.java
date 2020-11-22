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
public enum Gender {
    M("Male"), F("Female");
    
    private String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

}
