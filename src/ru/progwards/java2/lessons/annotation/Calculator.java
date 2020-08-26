package ru.progwards.java2.lessons.annotation;

public class Calculator {
    String expression;
    int pos;

    @Before
    public void init(String expression) {
        new Calculator(expression);
    }

    private Calculator(String expression) {
        this.expression = expression;
        pos = 0;
    }

    @Test(priority = 5)
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

    @Test(priority = 4)
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

    @Test
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

    @Test(priority = 2)
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

    @Test(priority = 1)
    public int calculate(String expression) throws Exception {
        return new Calculator(expression).expression();
    }

    @After
    public void stop() {
        expression=null;
        pos=0;
    }
}
