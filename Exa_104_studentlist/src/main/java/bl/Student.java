/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 10jon
 */
public class Student implements Serializable{
    private String firstname;
    private String lastname;
    private String classname;
    private int catNr;
    private String gender;
    private LocalDate dateOfBirth;

    public Student() {
    }
    
    private static Map<String, Integer> currentCatNr = new HashMap<>();

    public Student(String line){
        String[] tokens = line.split(";");
        this.classname = tokens[0];
        this.lastname = tokens[1];
        this.firstname = tokens[2];
        this.gender = tokens[3];
        String[] dateparts = tokens[4].split("-");
        int year = Integer.parseInt(dateparts[0]);
        int month = Integer.parseInt(dateparts[1]);
        int day = Integer.parseInt(dateparts[2]);
        this.dateOfBirth = LocalDate.of(year, month, day);
        
        Integer catNr = Student.currentCatNr.get(classname);
        if(catNr == null){
            catNr = 1;
            Student.currentCatNr.put(classname, catNr);
        }
        else{
            catNr++;
            Student.currentCatNr.remove(classname);
            Student.currentCatNr.put(classname, catNr);
        }
        this.catNr = catNr;
    }

    public Student(String firstname, String lastname, String classname, int catNr, String gender, LocalDate dateOfBirth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.classname = classname;
        this.catNr = catNr;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Student{" + "firstname=" + firstname + ", lastname=" + lastname + ", classname=" + classname + ", catNr=" + catNr + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", currentCatNr=" + currentCatNr + '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getCatNr() {
        return catNr;
    }

    public void setCatNr(int catNr) {
        this.catNr = catNr;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Map<String, Integer> getCurrentCatNr() {
        return currentCatNr;
    }

    public void setCurrentCatNr(Map<String, Integer> currentCatNr) {
        this.currentCatNr = currentCatNr;
    }

    
    
    
}
