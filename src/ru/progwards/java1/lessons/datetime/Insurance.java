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
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.*;


public class Insurance {

    private ZonedDateTime start;
    private Duration duration;

    public static enum FormatStyle {SHORT, LONG, FULL}

    public Insurance(ZonedDateTime start){
        this.start = start;
    }

    public Insurance(String strStart, FormatStyle style){
        ZonedDateTime zonedDateTime;
        switch (style){
            case FULL:
                zonedDateTime = ZonedDateTime.parse(strStart);
                this.start = zonedDateTime;
                break;
            case LONG:
                LocalDateTime localDateTime = LocalDateTime.parse(strStart, DateTimeFormatter.ISO_LOCAL_DATE_TIME); //  BASIC_ISO_DATE
                zonedDateTime = ZonedDateTime.of(localDateTime, Clock.systemDefaultZone().getZone());
                this.start = zonedDateTime;
                break;
            case SHORT:
                LocalDate localDate = LocalDate.parse(strStart, DateTimeFormatter.ISO_LOCAL_DATE);
                zonedDateTime = ZonedDateTime.of(localDate, LocalTime.of(0,0), Clock.systemDefaultZone().getZone());
                this.start = zonedDateTime;
                break;
        }
    }

    public void setDuration(Duration duration){
        this.duration = duration;
    }

    public void setDuration(ZonedDateTime expiration){
        this.duration = Duration.between(this.start, expiration);
    }

    public void setDuration(int months, int days, int hours){
        this.duration = Duration.between(this.start, this.start.plusMonths(months).plusDays(days).plusHours(hours));
    }

    public void setDuration(String strDuration, FormatStyle style){
        switch (style){
            case SHORT:
                long milSec = Long.parseLong(strDuration);
                this.duration = Duration.ofMillis(milSec);
                break;
            case FULL:
                this.duration = Duration.parse(strDuration);
                break;
            case LONG:
                LocalDateTime lDT = LocalDateTime.parse(strDuration, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                int year = lDT.getYear();
                int mont = lDT.getMonthValue();
                int days = lDT.getDayOfMonth();
                int hour = lDT.getHour();
                int minu = lDT.getMinute();
                int seco = lDT.getSecond();

                this.duration = Duration.between(this.start, this.start.plusYears(year).plusMonths(mont).plusDays(days).plusHours(hour).plusMinutes(minu).plusSeconds(seco));
                break;
        }
    }

    public boolean checkValid(ZonedDateTime dateTime){
        if (this.start.compareTo(dateTime) > 0 )
            return false;
        if (this.duration == null)
            return true;
        Duration durCurrent = Duration.between(this.start, dateTime);
        if (this.duration.compareTo(durCurrent) > 0)
            return true;
        return false;
    }

    public String toString() {
        if (checkValid(ZonedDateTime.now()))
            return "Insurance issued on " + start + " is valid";
        else
            return "Insurance issued on " + start + " is not valid";
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2020, Month.MAY, 10);
        String str = date.atStartOfDay(ZoneId.of("Europe/Moscow")).toString();
        Insurance insurance2 = new Insurance(str, FormatStyle.FULL);
        System.out.println(insurance2.toString());
    }
}
