package ru.progwards.java2.lessons.patterns;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profiler {

    public List <StatisticInfo> listOfSection= new ArrayList();

    private static volatile Profiler instance;

    private Profiler(){

    }

    public static Profiler getInstance() {
        Profiler localInstance = instance;
        if (localInstance == null) {
            synchronized (Profiler.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Profiler();
                }
            }
        }
        return localInstance;
    }

    public boolean contains(List <StatisticInfo> list, String name) {
        for (StatisticInfo item : list) {
            if (item.getSectionName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public String numberN(String name) {
        StringBuilder str = new StringBuilder();
        if (name == null || name.isEmpty()) {
            str.append(0);
        }
        for (int i = 0; i < name.length(); i++) {
            if (Character.isDigit(name.charAt(i))){
                str.append(name.charAt(i));
            }
        }
        return str.toString();
    }

    public void enterSection(String name){
        String str = getInstance().numberN(name);
        int n = Integer.parseInt(str);
        if (listOfSection.isEmpty() || !getInstance().contains(listOfSection, name)) {
            StatisticInfo statisticInfo = new StatisticInfo(name);
            listOfSection.add(statisticInfo);
            long startTime = new Date().getTime();
            statisticInfo.setStartTime(startTime);
        } else {
            long startTime = new Date().getTime();
            listOfSection.get(n-1).setStartTime(startTime);
            listOfSection.get(n-1).incCount();
        }
    }

    public void exitSection(String name){
        String str = getInstance().numberN(name);
        int n = Integer.parseInt(str);
        long c = 0;
        for (int i=1; i<10; i++){
            Integer x = n+i;
            if (getInstance().contains(listOfSection, x.toString())){
                c = c+listOfSection.get(n+i-1).fullTime;
            }
        }
        listOfSection.get(n-1).otherTimes = c;
        long exitTime = new Date().getTime();
        listOfSection.get(n-1).setEndTime(exitTime);
        listOfSection.get(n-1).setRazTime();
        if (listOfSection.get(n-1).count>1){
            listOfSection.get(n-1).setFullTime();
        } else {
            listOfSection.get(n-1).fullTime = listOfSection.get(n-1).razTime;
        }
        listOfSection.get(n-1).setSelfTime();
    }

    public List<StatisticInfo> getStatisticInfo(){
        for (int i = 1; i<listOfSection.size(); i++){
            listOfSection.get(i-1).selfTime = listOfSection.get(i-1).fullTime - listOfSection.get(i).fullTime;
        }
        for (StatisticInfo item : listOfSection) {
            System.out.println(" "+item.getSectionName()+" "+item.fullTime + " " + item.selfTime + " " + item.count);
        }
        return listOfSection;
    }

    public static void main(String[] args) {
        Profiler profiler = getInstance();
        profiler.enterSection("1");
        int i=0;
        profiler.enterSection("2");
        while (i<args.length){
            System.out.print(args[i] + " ");
        }
        profiler.exitSection("2");
        for(int j = 0; j<10000000; j++){
            j++;
        }
        profiler.exitSection("1");
        System.out.println(profiler.getStatisticInfo().toString());
    }
}