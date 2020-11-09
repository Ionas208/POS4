/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
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
    
    public Lesson(String line){
        String[] parts = line.split(";");
        String subject = parts[0];
        if(subject.equals("-")){
            subject = "";
        }
        List<String> teachers = new ArrayList<>();
        String[] teacher_parts = parts[1].split(",");
        for(int i = 0; i<teacher_parts.length; i++){
            if(!teacher_parts[i].equals("-")){
                teachers.add(teacher_parts[i]);
            } 
        }
        this.subject = subject;
        this.teachers = teachers;
        this.substitude = false;
    }

    public Lesson() {
    }

    @Override
    public String toString() {
        return subject;
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

    public String getStyleClasses(){
        String classes = "timetable_lesson ";
        if(isSubstitude()){
            classes += "timetable_lesson_substitude ";
        }
        if(subject.equals("")){
            classes += "timetable_lesson_free";
        }
        return classes;
    }
    
}
