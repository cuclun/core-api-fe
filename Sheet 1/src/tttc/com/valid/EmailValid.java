package tttc.com.valid;

import tttc.com.exception.EmailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValid {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

    public static void isEmailValid(String email) throws EmailException {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new EmailException("Email khong dung dinh dang!!");
        }
    }
}
