/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Lesson;
import beans.Weekday;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author 10jon
 */
public class SubstitudePlan {
    Map<Weekday, List<Lesson>> timetable = new HashMap<>();
    private String classname;

    public SubstitudePlan() {
    }
    
    public void addLesson(Weekday wd, Lesson l){
        List<Lesson> lessons_of_day = timetable.get(wd);
        if(lessons_of_day == null){
            lessons_of_day = new ArrayList<>();
        }
        lessons_of_day.add(l);
        timetable.remove(wd);
        timetable.put(wd, lessons_of_day);
    }
    
    public Lesson getLesson(Weekday wd, int hour){
        return timetable.get(wd).get(hour-1);
    }
    
    public List<Lesson> getLessonsForHour(int hour){
        System.out.println(timetable);
        List<Lesson> lessons_for_hour = new ArrayList<>();
        for(Weekday wd: Weekday.values()){
            Lesson l = null;
            try{
                l = getLesson(wd, hour);
            }
            catch(NullPointerException ex){
            }
            
            if(l==null){
                l = new Lesson("", new ArrayList<>(), false);
            }
            lessons_for_hour.add(l);
        }
        return lessons_for_hour;
    }
    
    public void loadTimetable(String filepath){        
        InputStream is = null;
        try {
            is = new FileInputStream(filepath);
            classname = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines().collect(Collectors.toList()).get(0);
            is = new FileInputStream(filepath);
            List<Lesson> lessons = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                    .lines()
                    .skip(1)
                    .map(Lesson::new)
                    .collect(Collectors.toList());
            int current_day = 0;
            for (Lesson lesson : lessons) {
                addLesson(Weekday.values()[current_day], lesson);
                current_day++;
                if(current_day % 5 == 0 && current_day != 0){
                    current_day = 0;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

    public String getClassname() {
        return classname;
    }
}