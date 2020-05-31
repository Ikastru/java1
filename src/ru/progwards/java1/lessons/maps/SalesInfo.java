package ru.progwards.java1.lessons.maps;

/**
 * Информация по продажам
 * <p>
 * Во входном файле находятся данные в CSV формате, CSV - Comma Separated Values, значения разделенные запятыми.
 * Каждая строка - данные об одной покупке. Входные данные
 * <p>
 * ФИ покупателя, наименование товара, количество, сумма
 * <p>
 * String, String, int, double
 * <p>
 * <p>
 * Пример
 * <p>
 * <p>
 * Иванов Сергей, iPhone 10X, 2, 150000
 * Петрова Анна, наушники JBL, 2, 7000
 * Иванов Сергей, чехол для iPhone, 1, 1000
 * Петрова Анна, пакет пластиковый, 1, 3
 * Радж Кумар, батарейка ААА, 1, 150
 * Михаил Цикло, iPhone 10X, 1, 75000
 * Радж Кумар, пакет пластиковый, 1, 3
 * Михаил Цикло, пакет пластиковый, 1, 3
 * Иванов Сергей, стекло защитное, 1, 1000
 * Василий Пупкин, спички, 10, 10
 * <p>
 * …
 * <p>
 * <p>
 * 3.1 Реализовать метод public int loadOrders(String fileName) - вернуть количество успешно загруженных строк.
 * Если в строке более или менее 4-x полей, или количество и сумма не преобразуются в числа -
 * эту строку не загружаем.
 * <p>
 * <p>
 * 3.2 Реализовать метод public Map<String, Double> getGoods() - вернуть список товаров, отсортированный по
 * наименованию товара. В String - наименование товара, в Double - общая сумма продаж по товару
 * <p>
 * 3.3 Реализовать метод public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() -
 * вернуть список покупателей, отсортированный по алфавиту. В String  - ФИ, в Double -
 * сумма всех покупок покупателя, в Integer - количество покупок
 * <p>
 * 3.4 Протестировать на данных из примера выше
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SalesInfo {

    public static List<Strloyee> strList = new ArrayList<>();

    public static class Strloyee {
        private String fio;
        private String device;
        private int quantity;
        private double cost;

        public String getfio() {
            return fio;
        }

        public void setFio(String fio) {
            this.fio = fio;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "::Имя " + getfio() + "::Устр-во " + getDevice() + "::Количество " + getQuantity() + "::Цена " + getCost();
        }
    }

    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }

    public static int loadOrders(String fileName) {
        //Количество нормально загруженных строк
        int count = 0;
        //Количество незагруженных строк
        int troublecount=0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = null;
            Scanner scanner = null;
            //Номер позиции столбца в CSV
            int index = 0;
            while ((line = reader.readLine()) != null) {
                Strloyee sle = new Strloyee();
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    int rule = 1;
                    String data = scanner.next();
                    if (index == 0) {
                        if (data.isEmpty() || isNumber(data)){
                            System.out.println("Некорректные данные::" + data);
                            rule = 0;
                        } else {
                            sle.setFio(data);
                        }
                    }
                    else if (index == 1) {
                        if (data.isEmpty()) {
                            System.out.println("Некорректные данные::" + data);
                            rule = 0;
                        } else {
                            sle.setDevice(data);
                        }
                    }
                    else if (index == 2) {
                        if ((!isNumber(data)) || data.isEmpty()){
                            System.out.println("Некорректные данные::" + data);
                            rule = 0;
                        } else {
                            sle.setQuantity((int) Float.parseFloat(data));
                        }
                    }
                    else if (index == 3) {
                        if ((!isNumber(data)) || data.isEmpty()) {
                            System.out.println("Некорректные данные::" + data);
                            rule = 0;
                        } else {
                            sle.setCost(Double.parseDouble(data));
                        }
                    }
//                    else if (index >= 4) {
//                        System.out.println("Некорректные данные::" + data);
//                        rule = 0;
//                    }
//                    else {
//                        System.out.println("Некорректные данные::" + data);
//                        rule = 0;
//                    }
                    index++;
                    if (rule == 0){
                        troublecount++;
                    }
                    if (rule == 1) {
                        strList.add(sle);
                        count++;
                    }
                }
                index = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return troublecount/2;
    }

    static Comparator<Strloyee> comparatorDevice = new Comparator<>() {
        @Override
        public int compare(Strloyee o1, Strloyee o2) {
            return o1.getDevice().compareTo(o2.getDevice());
        }
    };

    public static Map<String, Double> getGoods() {
//        Map<String, Double> mapGoods = (Map<String, Double>) new PriorityQueue(comparatorDevice);
        TreeMap<String, Double> mapGoods = new TreeMap<>();
        for (Strloyee strloyee : strList) {
            mapGoods.put(strloyee.getDevice(), (double) strloyee.getCost());
        }
        return mapGoods;
    }

    static Comparator<Strloyee> comparatorFio = new Comparator<>() {
        @Override
        public int compare(Strloyee o1, Strloyee o2) {
            return o1.getfio().compareTo(o2.getfio());
        }
    };

    public static Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() {
//        Map<String, AbstractMap.SimpleEntry<Double, Integer>> mapCustomers = (Map<String, AbstractMap.SimpleEntry<Double, Integer>>) new PriorityQueue(comparatorFio);
        TreeMap<String, AbstractMap.SimpleEntry<Double, Integer>> mapCustomers = new TreeMap<>();
        for (Strloyee strloyee : strList) {
            AbstractMap.SimpleEntry<Double, Integer> dobMap = new AbstractMap.SimpleEntry<Double, Integer>(strloyee.getCost(), strloyee.getQuantity());
            mapCustomers.put(strloyee.getfio(), dobMap);
        }
        return mapCustomers;
    }

    public static void main(String[] args) {
        loadOrders("C:\\Users\\Ikast\\IdeaProjects\\Helloworld\\src\\ExampleSalesInfo.txt");
        System.out.println(getGoods());
        System.out.println(getCustomers());
    }
}
