package ru.progwards.java1.lessons.datetime;

/**
 * Класс должен проверять валидность страховок, например для выезжающих за рубеж.
 * Каждая страховка имеет дату-время начала, и продолжительность.
 *
 *
 * 1.1  Реализовать локальный public static enum FormatStyle {SHORT, LONG, FULL} - стиль формата даты-времени
 *
 *
 * 1.2. Реализовать приватные свойства класса:
 * private ZonedDateTime start - дата-время начала действия страховки.
 * private Duration duration - продолжительность действия.
 *
 *
 * 1.2 Реализовать конструкторы:
 *
 * public Insurance(ZonedDateTime start) - установить дату-время начала действия страховки.
 *
 * public Insurance(String strStart, FormatStyle style) - установить дату-время начала действия страховки
 * SHORT соответствует ISO_LOCAL_DATE
 * LONG  - ISO_LOCAL_DATE_TIME
 * FULL - ISO_ZONED_DATE_TIME
 * Для вариантов, когда не задан явно часовой пояс использовать таковой по умолчанию.
 *
 *
 *
 * 1.3 Реализовать методы, устанавливающие продолжительность действия страховки:
 *
 * public void setDuration(Duration duration) - установить продолжительность действия страховки.
 *
 * public void setDuration(ZonedDateTime expiration) - установить продолжительность действия страховки,
 * задав дату-время окончания.
 *
 * public void setDuration(int months, int days, int hours) - установить продолжительность действия страховки,
 * задав целыми числами количество месяцев, дней и часов.
 *
 * public void setDuration(String strDuration, FormatStyle style) - установить продолжительность действия страховки
 * SHORT - целое число миллисекунд (тип long)
 * LONG  - ISO_LOCAL_DATE_TIME - как период, например “0000-06-03T10:00:00” означает, что
 * продолжительность действия страховки 0 лет, 6 месяцев, 3 дня 10 часов.
 * FULL - стандартный формат Duration, который получается через toString()
 *
 *
 *
 * 1.4 Реализовать методы возврата информации:
 *
 * public boolean checkValid(ZonedDateTime dateTime) - проверить действительна ли страховка
 * на указанную дату-время. Если продолжительность не задана считать страховку бессрочной.
 *
 * public String toString() - вернуть строку формата "Insurance issued on " + start + validStr,
 * где validStr = " is valid", если страховка действительна на данный момент и " is not valid",
 * если она недействительна.
 */

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Insurance {

    public static enum FormatStyle {SHORT, LONG, FULL};

    private ZonedDateTime start;
    private Duration duration;

    public Insurance(ZonedDateTime start){
        this.start = start;
    }

    public Insurance(String strStart, FormatStyle style){
        switch (style){
            case SHORT:{
                ZonedDateTime zdt = Instant.parse(strStart).atZone(ZoneId.systemDefault());
                this.start = ZonedDateTime.parse(DateTimeFormatter.ISO_LOCAL_DATE.format(zdt));
            }
            case LONG:{
                ZonedDateTime zdt = Instant.parse(strStart).atZone(ZoneId.systemDefault());
                this.start = ZonedDateTime.parse(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(zdt));
            }
            case FULL:{
                ZonedDateTime zdt = Instant.parse(strStart).atZone(ZoneId.systemDefault());
                this.start = ZonedDateTime.parse(DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zdt));
            }
            default:{
                this.start = Instant.parse(strStart).atZone(ZoneId.systemDefault());
            }
        }
    }

    public void setDuration(Duration duration){
        this.duration = duration;
    }

    public void setDuration(ZonedDateTime expiration){
        this.duration = Duration.between(start,expiration);
    }

    public void setDuration(int months, int days, int hours){
        String str = days + "." + months + "." + start.getYear() + " " + hours;
        ZonedDateTime endS = Instant.parse(str).atZone(ZoneId.systemDefault());
        this.duration = Duration.between(start,endS);
    }

    public void setDuration(String strDuration, FormatStyle style){
        switch (style){
            case SHORT:{
                this.duration = Duration.ofDays(Duration.parse(strDuration).toMillis());
            }
            case LONG:{
                ZonedDateTime zdt = Instant.parse(strDuration).atZone(ZoneId.systemDefault());
                Duration dur = Duration.parse(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(zdt));
                this.duration = dur;
            }
            case FULL:{
                this.duration = Duration.parse(strDuration);
            }
        }
    }

    public boolean checkValid(ZonedDateTime dateTime){
        boolean check;
        if (Duration.between(start,dateTime).compareTo(duration)==1){
            check = false;
        }else {
            check = true;
        }
        return check;
    }

    public String toString(){
        String str = "";
        if (checkValid(Instant.now().atZone(ZoneId.systemDefault()))){
            str =  "Insurance issued on " + start + " is valid";
        } else {
            str = "Insurance issued on " + start + " is not valid";
        }
        return str;
    }
}
