package ru.itis.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static final String loginRegex = "[а-яА-Яa-zA-Z][а-яА-Яa-zA-Z\\d]{2,19}";
    public static final String passwordRegex = "[а-яА-Яa-zA-Z\\d][а-яА-Яa-zA-Z\\d-]{4,23}[а-яА-Яa-zA-Z\\d]";
    public static final String emailRegex = "[а-яА-Яa-zA-Z]{3,20}@[a-z\\.]{3,15}\\.[a-z]{2,3}";
    public static final String nameRegex = "[а-яА-Яa-zA-Z]{2,20}";

    public static boolean validate(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
