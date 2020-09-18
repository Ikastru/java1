package ru.progwards.java2.lessons.patterns;

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

    public void setStartTime(long sTime) {
        this.sTime = sTime;
    }

    public void setEndTime(long eTime) {
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