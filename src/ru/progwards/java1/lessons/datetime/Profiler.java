package ru.progwards.java1.lessons.datetime;

/**
 * Реализовать класс для ручной профилировки производительности программного кода.
 * Профилировка программного кода - измерение времени выполнения отдельных фрагментов кода
 * с целью выявления узких мест в производительности.
 *
 * Реализовать методы
 * 2.1 public static void enterSection(String name) - войти в профилировочную секцию, замерить время входа.
 *
 * 2.2 public static void exitSection(String name) - выйти из профилировочной секции. Замерить время выхода,
 * вычислить промежуток времени между входом и выходом в миллисекундах.
 *
 * 2.3 public static List<StatisticInfo> getStatisticInfo() - получить профилировочную статистику,
 * отсортировать по наименованию секции
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profiler {

    public static List <StatisticInfo> listOfSection= new ArrayList();

    public static boolean contains(List <StatisticInfo> list, String name) {
        for (StatisticInfo item : list) {
            if (item.getSectionName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static String numberN(String name) {
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

    public static void enterSection(String name){
        String str = numberN(name);
        int n = Integer.parseInt(str);
        if (listOfSection.isEmpty() || !contains(listOfSection, name)) {
            StatisticInfo statisticInfo = new StatisticInfo(name);
            listOfSection.add(statisticInfo);
            long startTime = new Date().getTime();
            statisticInfo.setsTime(startTime);
        } else {
            long startTime = new Date().getTime();
            listOfSection.get(n-1).setsTime(startTime);
            listOfSection.get(n-1).incCount();
        }
    }

    public static void exitSection(String name){
        String str = numberN(name);
        int n = Integer.parseInt(str);
        long c = 0;
        for (int i=1; i<10; i++){
            Integer x = n+i;
            if (contains(listOfSection, x.toString())){
                c = c+listOfSection.get(n+i-1).fullTime;
            }
        }
        listOfSection.get(n-1).otherTimes = c;
        long exitTime = new Date().getTime();
        listOfSection.get(n-1).seteTime(exitTime);
        listOfSection.get(n-1).setRazTime();
        if (listOfSection.get(n-1).count>1){
            listOfSection.get(n-1).setFullTime();
        } else {
            listOfSection.get(n-1).fullTime = listOfSection.get(n-1).razTime;
        }
        listOfSection.get(n-1).setSelfTime();
    }

    public static List<StatisticInfo> getStatisticInfo(){
        for (StatisticInfo item : listOfSection) {
            System.out.println(" "+item.getSectionName()+" "+item.fullTime + " " + item.selfTime + " " + item.count);
        }
        return listOfSection;
    }

    public static void main(String[] args) {
        enterSection("1");
        int i=0;
        enterSection("2");
        while (i<args.length){
            System.out.print(args[i] + " ");
        }
        exitSection("2");
        for(int j = 0; j<1000; j++){
            j++;
        }
        exitSection("1");
        System.out.println(getStatisticInfo());
    }
}
