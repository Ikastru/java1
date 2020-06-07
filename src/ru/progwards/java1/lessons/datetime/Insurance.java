package ru.progwards.java1.lessons.datetime;

/**
 * Класс должен проверять валидность страховок, например для выезжающих за рубеж.
 * Каждая страховка имеет дату-время начала, и продолжительность.
 * <p>
 * <p>
 * 1.1  Реализовать локальный public static enum FormatStyle {SHORT, LONG, FULL} - стиль формата даты-времени
 * <p>
 * <p>
 * 1.2. Реализовать приватные свойства класса:
 * private ZonedDateTime start - дата-время начала действия страховки.
 * private Duration duration - продолжительность действия.
 * <p>
 * <p>
 * 1.2 Реализовать конструкторы:
 * <p>
 * public Insurance(ZonedDateTime start) - установить дату-время начала действия страховки.
 * <p>
 * public Insurance(String strStart, FormatStyle style) - установить дату-время начала действия страховки
 * SHORT соответствует ISO_LOCAL_DATE
 * LONG  - ISO_LOCAL_DATE_TIME
 * FULL - ISO_ZONED_DATE_TIME
 * Для вариантов, когда не задан явно часовой пояс использовать таковой по умолчанию.
 * <p>
 * 1.3 Реализовать методы, устанавливающие продолжительность действия страховки:
 * <p>
 * public void setDuration(Duration duration) - установить продолжительность действия страховки.
 * <p>
 * public void setDuration(ZonedDateTime expiration) - установить продолжительность действия страховки,
 * задав дату-время окончания.
 * <p>
 * public void setDuration(int months, int days, int hours) - установить продолжительность действия страховки,
 * задав целыми числами количество месяцев, дней и часов.
 * <p>
 * public void setDuration(String strDuration, FormatStyle style) - установить продолжительность действия страховки
 * SHORT - целое число миллисекунд (тип long)
 * LONG  - ISO_LOCAL_DATE_TIME - как период, например “0000-06-03T10:00:00” означает, что
 * продолжительность действия страховки 0 лет, 6 месяцев, 3 дня 10 часов.
 * FULL - стандартный формат Duration, который получается через toString()
 * <p>
 * 1.4 Реализовать методы возврата информации:
 * <p>
 * public boolean checkValid(ZonedDateTime dateTime) - проверить действительна ли страховка
 * на указанную дату-время. Если продолжительность не задана считать страховку бессрочной.
 * <p>
 * public String toString() - вернуть строку формата "Insurance issued on " + start + validStr,
 * где validStr = " is valid", если страховка действительна на данный момент и " is not valid", если она недействительна.
 */

import java.time.*;

import static java.time.format.DateTimeFormatter.*;


public class Insurance {

    public static enum FormatStyle {SHORT, LONG, FULL}


    private ZonedDateTime start;
    private Duration duration;

    public Insurance(ZonedDateTime start) {
        this.start = start;
    }

    public Insurance(String strStart, FormatStyle style) {
        switch (style) {
            case SHORT: {
                this.start = ZonedDateTime.parse(strStart, ISO_LOCAL_DATE);
//                this.start = ZonedDateTime.of(LocalDate.parse(strStart, DateTimeFormatter.ISO_LOCAL_DATE), LocalTime.MIDNIGHT, ZoneId.systemDefault());

//                ZonedDateTime zdt = Instant.parse(strStart).atZone(ZoneId.systemDefault());
//                this.start = ZonedDateTime.parse(DateTimeFormatter.ISO_LOCAL_DATE.format(zdt));
            }
            case LONG: {
                this.start = ZonedDateTime.parse(strStart, ISO_LOCAL_DATE_TIME);
//                this.start = ZonedDateTime.of(LocalDate.parse(strStart, DateTimeFormatter.ISO_LOCAL_DATE_TIME), LocalTime.MIDNIGHT, ZoneId.systemDefault());
//                ZonedDateTime zdt = Instant.parse(strStart).atZone(ZoneId.systemDefault());
//                this.start = ZonedDateTime.parse(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(zdt));
            }
            case FULL: {
                this.start = ZonedDateTime.parse(strStart, ISO_ZONED_DATE_TIME);
//                this.start = ZonedDateTime.of(LocalDate.parse(strStart, DateTimeFormatter.ISO_ZONED_DATE_TIME), LocalTime.MIDNIGHT, ZoneId.systemDefault());
//                ZonedDateTime zdt = Instant.parse(strStart).atZone(ZoneId.systemDefault());
//                this.start = ZonedDateTime.parse(DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zdt));
            }
            default: {
                this.start = ZonedDateTime.parse(strStart);
            }
        }
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setDuration(ZonedDateTime expiration) {
        this.duration = Duration.between(start, expiration);
    }

    public void setDuration(int months, int days, int hours) {
        long mills = hours * 60 * 60 * 1000 + days * 24 * 60 * 60 * 1000 + months * 30 * 24 * 60 * 60 * 1000;
        this.duration = Duration.ofMillis(mills);
    }

    public void setDuration(String strDuration, FormatStyle style) {
        switch (style) {
            case SHORT: {
                this.duration = Duration.ofMillis(Duration.parse(strDuration).toMillis());
            }
            case LONG: {
                ZonedDateTime zdt = ZonedDateTime.parse(strDuration, ISO_LOCAL_DATE_TIME);
//                ZonedDateTime zdt = Instant.parse(strDuration).atZone(ZoneId.systemDefault());
                Duration dur = Duration.parse(ISO_LOCAL_DATE_TIME.format(zdt));
                this.duration = dur;
            }
            case FULL: {
                this.duration = Duration.parse(strDuration);
            }
        }
    }

    public boolean checkValid(ZonedDateTime dateTime) {
        boolean check;
        try {
            if (Duration.between(start, dateTime).compareTo(duration) == 1) {
                check = false;
            } else {
                check = true;
            }
        } catch (Exception e) {
            check = true;
        }
        return check;
    }

    public String toString() {
        String str = "";
        if (checkValid(Instant.now().atZone(ZoneId.systemDefault()))) {
            str = "Insurance issued on " + start + " is valid";
        } else {
            str = "Insurance issued on " + start + " is not valid";
        }
        return str;
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2020, Month.MAY, 10);
        String str = date.atStartOfDay(ZoneId.of("Europe/Moscow")).toString();
//        Insurance insurance1 = new Insurance(date.atStartOfDay(ZoneId.of("Europe/Moscow")));
        Insurance insurance2 = new Insurance(str, FormatStyle.FULL);
        System.out.println(insurance2.toString());
    }
}
