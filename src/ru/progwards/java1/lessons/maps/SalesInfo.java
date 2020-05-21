package ru.progwards.java1.lessons.maps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SalesInfo {

    public class Strloyee {

        private String fio;
        private String device;
        private int quantity;
        private int cost;

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
        public int getCost() {
            return cost;
        }
        public void setCost(int cost) {
            this.cost = cost;
        }

        @Override
        public String toString(){
            return "::Имя " + getfio() + "::Устр-во " + getDevice() + "::Количество " + getQuantity() + "::Цена " + getCost();
        }
    }

    public int loadOrders(String fileName){
        int count = 0;
        List<Strloyee> strList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = null;
            Scanner scanner = null;
            int index = 0;
            int rule = 1;
            while ((line = reader.readLine()) != null){
                Strloyee sle = new Strloyee();
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                while (scanner.hasNext()){
                    String data = scanner.next();
                    if (index == 0)
                        sle.setFio(data);
                    else if (index == 1)
                        sle.setDevice(data);
                    else if (index == 2)
                        sle.setQuantity(Integer.parseInt(data));
                    else if (index == 3)
                        sle.setCost(Integer.parseInt(data));
                    else {
                        System.out.println("Некорректные данные::" + data);
                        rule = 0;
                    }
                    index++;
                }
                index = 0;
                if (rule ==1) {
                    strList.add(sle);
                    count++;
                }
                rule=1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
