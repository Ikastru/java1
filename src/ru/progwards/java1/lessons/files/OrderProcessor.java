package ru.progwards.java1.lessons.files;

/**
 * Информация о заказах поступает в виде файлов, которые лежат в под-папках разбитых по неделям, имена папок не имеют значения.
 * Имя каждого файла имеет формат: AAA-999999-ZZZZ.csv где AAA - обязательные 3 символа shopId - идентификатор магазина,
 * 999999 - обязательные 6 символов orderId - номер заказа, ZZZZ - обязательные 4 символа customerId -
 * идентификатор покупателя, расширение файла - csv, например S02-P01X12-0012.csv: shopId=”S02”, orderId=”P01X12”, customerId=”0012”
 *
 * Содержимое каждого файла имеет формат CSV (Comma Separated Values) со следующими данными
 *
 * Наименование товара, количество, цена за единицу
 *
 *
 * Например:
 *
 * Игрушка мягкая “Мишка”, 1, 1500
 * Пазл “Замок в лесу”, 2, 700
 * Книжка “Сказки Пушкина”, 1, 300
 *
 * Реализовать класс OrderProcessor со следующими методами
 *
 * 3.3 конструктор public OrderProcessor(String startPath) - инициализирует класс, с указанием начальной папки для хранения файлов
 *
 * 3.4 метод public int loadOrders(LocalDate start, LocalDate finish, String shopId) - загружает заказы за указанный диапазон дат,
 * с start до finish, обе даты включительно. Если start == null, значит нет ограничения по дате слева, если finish == null,
 * значит нет ограничения по дате справа, если shopId == null - грузим для всех магазинов При наличии хотя бы одной ошибке
 * в формате файла, файл полностью игнорируется, т.е. Не поступает в обработку. Метод возвращает количество файлов с ошибками.
 * При этом, если в классе содержалась информация, ее надо удалить
 *
 *
 * 3.5 метод public List<Order> process(String shopId) - выдать список заказов в порядке обработки (отсортированные по дате-времени),
 * для заданного магазина. Если shopId == null, то для всех
 *
 * 3.6 метод public Map<String, Double> statisticsByShop() - выдать информацию по объему продаж по магазинам
 * (отсортированную по ключам): String - shopId, double - сумма стоимости всех проданных товаров в этом магазине
 *
 * 3.7 метод public Map<String, Double> statisticsByGoods() - выдать информацию по объему продаж по товарам
 * (отсортированную по ключам): String - goodsName, double - сумма стоимости всех проданных товаров этого наименования
 *
 * 3.8 метод public Map<LocalDate, Double> statisticsByDay() - выдать информацию по объему продаж по дням
 * (отсортированную по ключам): LocalDate - конкретный день, double - сумма стоимости всех проданных товаров в этот день
 */

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class OrderProcessor {
    public String startPath;
    public List<Order> listOrder;
    public static double sumOrder;

    public OrderProcessor(String startPath) {
        this.startPath = startPath;
        listOrder = new ArrayList<>();
    }

    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        //  количество ошибок
        int count = 0;
        String pattern;
        if (shopId == null)
            pattern = "glob:**/???-??????-????.csv";
        else
            pattern = "glob:**/" + shopId + "-??????-????.csv";
        List<Path> fList = createList(this.startPath, pattern);
        //  файл для загрузки
        for (Path file : fList) {
            String name = file.getFileName().toString();
            String shop = name.substring(0, 3);
            String orde = name.substring(4, 10);
            String cust = name.substring(11, 15);
            String last = null;
            try {
                last = Files.getAttribute(file, "basic:lastModifiedTime").toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            LocalDateTime lastTime = checkLastTime(last.substring(0, 10) + " " + last.substring(11, 19));
            LocalDate lastDate = lastTime.toLocalDate();
            if (checkOrders(start, finish, lastDate)) {
                sumOrder = 0d;
                List<OrderItem> oList = orderItem(file);
                if (oList == null) {
                    count++;
                    continue;
                }
                listOrder.add(new Order(shop, orde, cust, lastTime, oList, sumOrder));
            }
        }
        return count;
    }

    //  обработка содержимого одного файла
    public List<OrderItem> orderItem(Path file) {
        String googsName = "";
        int count = 0;
        double price= 0d;
        List<OrderItem> oList = new ArrayList<>();
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<String> it1 = allLines.iterator();
        boolean good = true;
        while (it1.hasNext()) {

            try(Scanner scanner = new Scanner(it1.next()).useDelimiter("\\s*,\\s*")) {
                googsName = scanner.next();
                count = scanner.nextInt();
                price = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Ошибка в строке");
                good = false;
                break;
            }
            oList.add(new OrderItem(googsName, count, price));
            sumOrder += count * price;
        }
        if (good) {
            oList.sort((a, b) -> a.getGoogsName().compareTo(b.getGoogsName()));
            return oList;
        }
        else
            return null;
    }

    //  проверить, удовлетворяет ли файл условиям по дате
    public boolean checkOrders(LocalDate start, LocalDate finish, LocalDate lastDate){
        boolean left = false, right = false;
        if (start == null)
            left = true;
        else
            left = (lastDate.compareTo(start) >= 0);
        if (finish == null)
            right = true;
        else
            right = (lastDate.compareTo(finish) <= 0);
        return left && right;
    }

    public LocalDateTime checkLastTime(String last){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.from(dtf.parse(last));
        ZonedDateTime zdtAtDefault = ldt.atZone( ZoneOffset.ofHours(0) );
        ZoneId zoneId = ZoneId.of( "Europe/Moscow" );
        ZonedDateTime zdtAtCurrent = zdtAtDefault.withZoneSameInstant( zoneId );
        return zdtAtCurrent.toLocalDateTime();
    }

    public static List<Path> createList(String startPath, String pattern){
        final Path dir = Paths.get(startPath);
        List<Path> fList = new ArrayList<>();
        try {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);

            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {

                    if (pathMatcher.matches(dir.relativize(path)))
                        fList.add(path);
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e){
            System.out.println(e);
        }
        return fList;
    }

    public List<Order> process(String shopId){
        List<Order> processList = new ArrayList<>();
        if (shopId == null)
            processList.addAll(listOrder);
        else
            listOrder.forEach(orders -> {
                if (orders.shopId.equals(shopId))
                    processList.add(orders);
            });
        processList.sort((a,b)->a.getDatetime().compareTo(b.getDatetime()));
        return processList;
    }

    public Map<String, Double> statisticsByShop(){
        Map<String, Double> mapByShop =
                listOrder.stream().collect(Collectors.groupingBy(Order::getShopId, Collectors.summingDouble(Order::getSum)));
        Map<String, Double> mapByShopSort = new TreeMap<>(mapByShop);
        return mapByShopSort;
    }

    public Map<String, Double> statisticsByGoods(){
        //  собрать все стороки накладных в единый список
        List<OrderItem> listOrderItem = new ArrayList<>();
        for (Order oneOrder : listOrder) {
            listOrderItem.addAll(oneOrder.items);
        }
        Map<String, Double> mapByGoods =
                listOrderItem.stream().collect(Collectors.groupingBy(OrderItem::getGoogsName, Collectors.summingDouble(OrderItem::getSumma)));
        Map<String, Double> mapByGoodsSort = new TreeMap<>(mapByGoods);
        return mapByGoodsSort;
    }
    public Map<LocalDate, Double> statisticsByDay(){
        Map<LocalDate, Double> mapByDay =
                listOrder.stream().collect(Collectors.groupingBy(Order::getDate, Collectors.summingDouble(Order::getSum)));
        Map<LocalDate, Double> mapByDaySort = new TreeMap<>(mapByDay);
        return mapByDaySort;
    }

}
