package tttc.com.valid;

import tttc.com.exception.DateException;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateValid {
    public static void isValidDate(Date date) throws DateException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.YEAR) < 1900 || calendar.get(Calendar.YEAR) > LocalDate.now().getYear()) {
            throw new DateException("Bạn phải nhập từ năm 1900 đến nay!");
        }
    }
}
