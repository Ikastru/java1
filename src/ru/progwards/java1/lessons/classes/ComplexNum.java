package ru.progwards.java1.lessons.classes;

public class ComplexNum {

    private int re;
    private int im;

    public ComplexNum(int a, int b){
        this.re = a;
        this.im = b;
    }

    public String toString(){
        return re + "+" + im + "i";
    }

    public ComplexNum add(ComplexNum num){
        ComplexNum c = this;
        int real = c.re + num.re;
        int imag = c.im + num.im;
        return new ComplexNum(real, imag);
    }

    public ComplexNum sub(ComplexNum num){
        ComplexNum c = this;
        int real = c.re - num.re;
        int imag = c.im - num.im;
        return new ComplexNum(real, imag);
    }

    public ComplexNum mul(ComplexNum num){
        ComplexNum c = this;
        int real = c.re * num.re - c.im * num.im;
        int imag = c.re * num.im + c.im * num.re;
        return new ComplexNum(real, imag);
    }

    public ComplexNum reciprocal() {
        int scale = re*re + im*im;
        return new ComplexNum(re / scale, -im / scale);
    }

    public ComplexNum div2(ComplexNum num){
        ComplexNum c = this;
        return c.mul(num.reciprocal());
    }

    public ComplexNum div(ComplexNum num){
        ComplexNum c = this;
        int real = (c.re*num.re + c.im*num.im)/(num.re*num.re+num.im*num.im);
        int imag = ((c.im*num.re - c.re*num.im)/(num.re*num.re+num.im*num.im));
        return new ComplexNum(real, imag);
    }

    public static void main(String[] args) {
        ComplexNum complexNum1=new ComplexNum(3,5);
        ComplexNum complexNum2=new ComplexNum(7,2);

        System.out.println(complexNum1.toString());
        System.out.println(complexNum2.toString());
        System.out.println(complexNum1.add(complexNum2));
        System.out.println(complexNum1.sub(complexNum2));
        System.out.println(complexNum1.mul(complexNum2));
        System.out.println(complexNum1.div(complexNum2));
        System.out.println(complexNum1.div2(complexNum2));
    }


}
