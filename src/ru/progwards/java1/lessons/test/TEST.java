package ru.progwards.java1.lessons.test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TEST {

    public static ZonedDateTime parseZDT(String str) {
        DateTimeFormatter dtf =DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS Z zzzz");
        ZonedDateTime mzt = ZonedDateTime.parse(str, dtf.withZone(ZoneId.of("Europe/Moscow")));
        return mzt;
    }

    public static void main(String[] args) {
        System.out.println(parseZDT("01.01.2020 16:27:14.444 +0300 Moscow Standard Time"));

    }

}
