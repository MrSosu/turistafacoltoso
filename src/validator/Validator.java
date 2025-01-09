package validator;

import java.util.Date;

public class Validator {

    public static Object requireNotNull(Object o) {
        if (o == null) {
            throw new NullPointerException("L'input è null");
        }
        return o;
    }

    public static String requireNotBlank(String s) {
        if (s == null || s.isBlank()) {
            throw new NullPointerException("La stringa è null o blank");
        }
        return s;
    }

    public static String validRegex(String toValidate, String regex) {
        if (toValidate == null || toValidate.isBlank()) {
            throw new NullPointerException("L'input è null");
        }
        if (!toValidate.matches(regex)) {
            throw new IllegalArgumentException("Input non valido, non rispetta la regex!");
        }
        return toValidate;
    }

    public static Integer requirePositive(Integer n) {
        if (n == null) {
            throw new NullPointerException("L'input è null");
        }
        if (n < 0) {
            throw new IllegalArgumentException("L'input deve essere positivo!");
        }
        return n;
    }

    public static Double requirePositive(Double n) {
        if (n == null) {
            throw new NullPointerException("L'input è null");
        }
        if (n < 0) {
            throw new IllegalArgumentException("L'input deve essere positivo!");
        }
        return n;
    }

    public static Integer requireGreaterThenZero(Integer n) {
        if (n == null) {
            throw new NullPointerException("L'input è null");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("L'input deve essere positivo!");
        }
        return n;
    }

    public static Double requireGreaterThenZero(Double n) {
        if (n == null) {
            throw new NullPointerException("L'input è null");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("L'input deve essere positivo!");
        }
        return n;
    }

    public static Integer requireBetween(Integer a, Integer b, Integer c) {
        if (a == null || b == null || c == null) {
            throw new NullPointerException("L'input è null");
        }
        if (a < b || a > c) {
            throw new IllegalArgumentException("Fuori dall'intervallo allo allo allo!");
        }
        return a;
    }


    public static Date requireDateBefore(Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null) {
            throw new NullPointerException("L'input è null");
        }
        if (firstDate.after(secondDate)) {
            throw new IllegalArgumentException("Strappa sto calendario va");
        }
        return firstDate;
    }

    public static Date requireDateAfter(Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null) {
            throw new NullPointerException("L'input è null");
        }
        if (firstDate.before(secondDate)) {
            throw new IllegalArgumentException("Strappa sto calendario va");
        }
        return firstDate;
    }


}
