package ru.progwards.java1.lessons.abstractnum;

/**
 * 6 Реализовать класс Pyramid, переопределяющий метод
 * public Number volume(), который будет возвращать объем пирамиды, с основанием квадрат,
 * и высотой равной стороне квадрата по формуле Segment*Segment*Segment/3;
 */

public class Pyramid extends Figure3D {

    public Pyramid(Number segment){
        super(segment);
    }

    @Override
    public Number volume(){
        Number numb = new Number();
        if (segment instanceof IntNumber)
            //Для точного целочисленного деления на 3 оно должно выполняться в конце
//            numb =  segment.div(new IntNumber(3)).mul(segment.mul(segment));
            numb = segment.mul(segment.mul(segment)).div(new IntNumber(3));
        else if (segment instanceof DoubleNumber)
            numb = segment.mul(segment.mul(segment.div(new DoubleNumber(3))));
        return numb;
    }
}
