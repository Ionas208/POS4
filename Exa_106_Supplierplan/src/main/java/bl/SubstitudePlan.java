/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Lesson;
import beans.Weekday;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 10jon
 */
public class SubstitudePlan {
    Map<Weekday, List<Lesson>> timetable = new HashMap<>();

    public SubstitudePlan() {
    }
    
    public void addLesson(Weekday wd, Lesson l){
        List<Lesson> lessons_of_day = timetable.get(wd);
        if(lessons_of_day == null){
            lessons_of_day = new ArrayList<>();
            lessons_of_day.add(l);
        }
        timetable.remove(wd);
        timetable.put(wd, lessons_of_day);
    }
    
    public Lesson getLesson(Weekday wd, int hour){
        return timetable.get(wd).get(hour-1);
    }
    
    public List<Lesson> getLessonsForHour(int hour){
        List<Lesson> lessons_for_hour = new ArrayList<>();
        for(Weekday wd: Weekday.values()){
            Lesson l = null;
            try{
                l = getLesson(wd, hour);
            }
            catch(NullPointerException ex){
            }
            
            if(l==null){
                l = new Lesson("", new ArrayList<String>(), false);
            }
            lessons_for_hour.add(l);
        }
        return lessons_for_hour;
    }
    
}