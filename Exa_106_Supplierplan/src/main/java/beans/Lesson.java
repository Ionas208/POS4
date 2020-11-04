/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;

/**
 *
 * @author 10jon
 */
public class Lesson {
    private String subject;
    private List<String> teachers;
    private boolean substitude;

    public Lesson(String subject, List<String> teachers, boolean substitude) {
        this.subject = subject;
        this.teachers = teachers;
        this.substitude = substitude;
    }

    public Lesson() {
    }

    @Override
    public String toString() {
        return "Lesson{" + "subject=" + subject + ", teachers=" + teachers + ", substitude=" + substitude + '}';
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<String> teachers) {
        this.teachers = teachers;
    }

    public boolean isSubstitude() {
        return substitude;
    }

    public void setSubstitude(boolean substitude) {
        this.substitude = substitude;
    }

    
    
}
