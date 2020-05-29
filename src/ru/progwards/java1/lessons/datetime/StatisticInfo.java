package ru.progwards.java1.lessons.datetime;

/**
 * 2.4 Реализовать class StatisticInfo
 * public String sectionName; - имя секции
 * public int fullTime - полное время выполнения секции в миллисекундах.
 * public int selfTime - чистое время выполнения секции в миллисекундах.
 * Для вложенных секций, из времени выполнения внешней секции нужно вычесть времена выполнения вложенных секций.
 * public int count - количество вызовов. В случае, если вызовов более одного,
 * fullTime и selfTime содержат суммарное время выполнения всех вызовов.
 */

public class StatisticInfo {
    public String sectionName;
    public long sTime;
    public long eTime;
    public long fullTime;
    public long razTime;
    public long selfTime;
    public long otherTimes;
    public int count=0;

    public StatisticInfo(String sectionName) {
        this.sectionName = sectionName;
        this.count++;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setsTime(long sTime) {
        this.sTime = sTime;
    }

    public void seteTime(long eTime) {
        this.eTime = eTime;
    }

    public void setRazTime() {
        this.razTime = eTime-sTime;
    }


    public void setFullTime() {
        this.fullTime += razTime;
    }

    public void incCount(){
        this.count++;
    }

    public void setSelfTime() {
        this.selfTime = fullTime-otherTimes;
    }
}
