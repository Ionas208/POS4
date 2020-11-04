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
public enum Weekday {
    MON("Mo", "Montag"),
    TUE("Di", "Dienstag"),
    WED("Mi", "Mittwoch"),
    THU("Do", "Donnerstag"),
    FRI("Fr", "Freitag");
    
    
    private String day_short;
    private String day_full;

    private Weekday(String day_short, String day_full) {
        this.day_short = day_short;
        this.day_full = day_full;
    }

    public String getDay_short() {
        return day_short;
    }

    public String getDay_full() {
        return day_full;
    }
    
    
}
