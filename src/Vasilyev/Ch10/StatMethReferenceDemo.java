package Vasilyev.Ch10;

//  класс со статическими методами:
class MyClass2{
    // методом отображаетс¤ сообщение:
    static void show(){
        System.out.println("метод класса MyClass2");
    }
    // методом вычисляется сумма чисел:
    static int sum(int n){
        int k,s=0;
        for(k=1;k<=n;k++){
            s+=k;
        }
        return s;
    }
}
// первый интерфейс:
interface MyShow{
    void myshow();
}
// второй интерфейс:
interface MySum{
    int mysum(int n);
}
// третий интерфейс:
interface MyPrinter{
    void myprint(Object t);
}
// главный класс:
class StatMethReferenceDemo{
    // главный метод:
    public static void main(String[] args){
        // использование ссылок на статические методы:
        MyShow A=MyClass2::show;
        MySum B=MyClass2::sum;
        MyPrinter P=System.out::println;
        // вызов методов из интерфейсных переменных:
        A.myshow();
        P.myprint("сумма чисел: "+B.mysum(10));
    }
}
