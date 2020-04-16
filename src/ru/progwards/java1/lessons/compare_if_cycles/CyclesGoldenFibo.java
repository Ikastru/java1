package ru.progwards.java1.lessons.compare_if_cycles;


public class CyclesGoldenFibo {

    public static boolean containsDigit(int number, int digit){
        int counter=0;
        String str="";
        String str1="";
        str=Integer.toString(number);
        str1=Integer.toString(digit);
                    if (str.contains(str1)) {
                        counter++;
                    }
        if (counter>0)
            return true;
        else
            return false;
    }

    public static int fiboNumber(int n){
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fiboNumber(n - 1) + fiboNumber(n - 2);
        }
    }

    public static boolean isGoldenTriangle(int a, int b, int c){
        if (a==b && ((1.61703<a/c || a/c<1.61903) || (1.61703<b/c || b/c<1.61903))) {
            return true;
        } else if (b==c && ((1.61703<b/a || b/a<1.61903) || (1.61703<c/a || c/a<1.61903))){
            return true;
        } else if (a==c && ((1.61703<a/b || a/b<1.61903) || (1.61703<c/b || c/b<1.61903))){
            return true;
        }
        else
            return false;
    }

    public static void main(String[] args) {
        for (int i=1; i<=15; i++){
            System.out.print(fiboNumber(i) + " ");
        }
        System.out.println(" ");
        System.out.println(containsDigit(0, 0));
        System.out.println(isGoldenTriangle(3,4,5));

        int counter=0;
        for (int j = 1; j<=100; j++){
            for (int k=1; k<=100; k++){
                for (int t=1; t<100; t++){
                    if (isGoldenTriangle(j, k, t)) {
                        counter++;
                        System.out.println(j+" "+k+" "+t+" ");
                    }
                }
            }
        }
        System.out.println(counter);
    }

}
