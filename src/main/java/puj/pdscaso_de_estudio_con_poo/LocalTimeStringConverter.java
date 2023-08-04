package puj.pdscaso_de_estudio_con_poo;

import javafx.util.StringConverter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalTimeStringConverter extends StringConverter<LocalTime> {

    private final DateTimeFormatter formatter;

    public LocalTimeStringConverter(DateTimeFormatter formatter, String pattern) {
        this.formatter = formatter;
    }

    @Override
    public String toString(LocalTime object) {
        if (object == null) {
            return "";
        }
        return formatter.format(object);
    }

    @Override
    public LocalTime fromString(String string) {
        if (string == null || string.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalTime.parse(string, formatter);
        } catch (DateTimeParseException e) {
            return null; // Return null if the input is not a valid LocalTime
        }
    }
}
