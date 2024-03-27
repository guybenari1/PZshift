package model;

import com.kosherjava.zmanim.hebrewcalendar.*;
import java.time.LocalDate;
import java.util.Date;

public class Holidays {
    private Date date;

    public Holidays(int day, int month, int year) {
        this.date = new Date(year-1900, month-1, day);
    }

    public boolean isHoliday() {
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

    public static String holidaysInWeek(){
        LocalDate date;
        Holidays holidayDate;
        String holidays="";
        for (int i=0; i<7; i++){
            date = LocalDate.now().plusDays(i);
            holidayDate = new Holidays(date.getDayOfMonth(),date.getMonthValue(), date.getYear());
            if (holidayDate.isHoliday()){
                holidays+=holidayDate.whichHoliday() +" on "+ date.getDayOfWeek() +"\n";
            }
        }
        if (holidays.isEmpty()){
            return null;
        }
        return holidays;
    }
}
