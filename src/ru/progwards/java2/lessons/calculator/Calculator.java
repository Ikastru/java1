package ru.progwards.java2.lessons.calculator;

/**
 * Лучший вариант калькулятора
 */

public class Calculator {
    String expression;
    int pos;

    public Calculator(String expression) {
        this.expression = expression;
        pos = 0;
    }

    boolean hasNext() {
        return pos < expression.length();
    }

    String getSym() throws Exception {
        if (!hasNext())
            throw new Exception("неожиданный конец выражения");
        System.out.println(expression.substring(pos, pos +1));
        return expression.substring(pos,++pos);
    }

    String checkSym() {
        System.out.println(expression.substring(pos, pos +1));
        return expression.substring(pos, pos +1);
    }

    int getNum() throws Exception {
        return Integer.parseInt(getSym());
    }

    String getOper() throws Exception {
        return getSym();
    }

    String checkOper() {
        return checkSym();
    }

    int term2() throws Exception{
        if (checkSym().equals("(")) {
            getSym();
            int res = expression();
            if (!getSym().equals(")"))
                throw new Exception("')' ожидалась");
            return res;
        } else
            return getNum();
    }

    int term() throws Exception {
        int res = term2();
        while (hasNext()) {
            String op = checkOper();
            if ("*/".contains(op)) {
                getOper();
                int n = term2();
                switch (op) {
                    case "*":
                        res *= n;
                        break;
                    case "/":
                        res /= n;
                        break;
                    default:
                        throw new Exception("не известная операция" + op);
                }
            } else
                return res;
        }
        return res;
    }

    int expression() throws Exception {
        int res = term();
        while (hasNext()) {
            String op = checkOper();
            if ("+-".contains(op)) {
                getOper();
                int n = term();
                switch (op) {
                    case "+":
                        res += n;
                        break;
                    case "-":
                        res -= n;
                        break;
                    default:
                        throw new Exception("не известная операция" + op);
                }
            }
            else return res;
        }
        return res;
    }

    // expression ::= number { (+-*/) number }

    // expression ::= term ("+"|"-") term
    // term ::= temr2 ("*"|"/") term2
    // term2 :: "(" expression ")" | number

    static int calculate(String expression) throws Exception {
        return new Calculator(expression).expression();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Calculator.calculate("(3+2)*3"));
    }
    // "3+2*3"
    // 3 2 3 * +
    // "(3+2)*3"
    // 3 2 + 3 *
}

