package model;

import com.kosherjava.zmanim.*;
import com.kosherjava.zmanim.util.*;
import com.kosherjava.zmanim.hebrewcalendar.*;

import java.util.Date;

public abstract class Holidays {
    private Date date;

    public Holidays(int day, int month, int year) {
        this.date = new Date(year-1900, month-1, day);
    }

    // JewishDate jewishDate = new JewishDate(date);
    public boolean isHoliday() {
        // Date date = new Date(122,month,day); // year 122 + *1900* = 2022 , month 0-11
        JewishCalendar jewishCalendar = new JewishCalendar(date);
        jewishCalendar.setInIsrael(true);
        if (jewishCalendar.getYomTovIndex() != -1) {
            return true;
        }
        return false;
    }

    public String whichHoliday() {
        HebrewDateFormatter hebrewDateFormatter = new HebrewDateFormatter();
        JewishCalendar jewishCalendar = new JewishCalendar(date);
        return hebrewDateFormatter.formatYomTov(jewishCalendar);
    }
}
