package sample;

import javafx.util.StringConverter;

/**
 * Created by ) on 12.04.2017.
 */
public class DoubleUtil extends StringConverter <Double>{
    // TODO дописать проверку валидности строки, учесть, что вводить число могут через "," и через "."
    public static String valid(String str) {

        return str;
    }

    @Override
    public String toString(Double obj) {
        return obj.toString();
    }

    @Override
    public Double fromString(String string) {
        valid(string);
        return Double.parseDouble(string);
    }
}


