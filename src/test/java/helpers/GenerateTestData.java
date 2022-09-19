package helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GenerateTestData {
    Calendar c = Calendar.getInstance();

    public String getTomorrowDateString(int amount) {
        Date date = new Date();
        c.setTime(date);
        c.add(Calendar.DATE, amount);
        Date nextDate = c.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(nextDate);
    }

    public Long getTomorrowTimeMillis(int amount){
        Long epoch = System.currentTimeMillis();
        c.setTimeInMillis(epoch);
        c.add(Calendar.DATE,amount);
        Long epochPlus = c.getTimeInMillis();
        return epochPlus;
    }

    public String getMonthYear(long timeMillis){
        Date date = new Date(timeMillis);
        SimpleDateFormat format = new SimpleDateFormat("LLLL YYYY", Locale.getDefault());
        return format.format(date);
    }

    public String getTomorrowDateStringFormat(int amount) {
        Date date = new Date();
        c.setTime(date);
        c.add(Calendar.DATE, amount);
        Date nextDate = c.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM YYYY года");
        return dateFormat.format(nextDate);
    }

}
