package ru.progwards.java1.lessons.test;

//Это классы, которые понадобятся для сортировки многоязыковых строк
import java.text.Collator;
import java.text.CollationKey;
import java.util.Locale;

/**
*Этот класс определяет набор статических методов для эффективной сортировки
 * массивов строк или других объектов. Он определяет также два интерфейса,
 * обеспечивающие два различных способа сравнения сортирукимых объектов
 **/
public class Sorter {

    public static interface Comparer {
        /**
         * if (a > b) return > 0;
         * if (a == b) return 0;
         * if (a < b) return < 0.
         **/
        public int compare(Object a, Object b);
    }

    public static interface Comparable {
        /**
         * if (this > other) return > 0
         * if (this == other) return 0
         * if (this < other) return < 0
         **/
        public int compareTo(Object other);
    }

    /**
     * Это внутренний объект, созданный анонимным классом,
     * сравнивающий две строки ASCII. Он используется ниже
     * методами sortASCII
     **/
    private static Comparer ascii_comparer = new Comparer() {
        public int compare(Object a, Object b) {
            return ((String)a).compareTo((String)b);
        }
    };

    /**
     * Для сравнения двух объетов Comparable
     **/
    private static Comparer comparable_comparer = new Comparer() {
        public int compare(Object a, Object b) {
            return ((Comparable)a).compareTo(b);
        }
    };

    /**
     *Массив строк ASCII сортируется по возрастанию
     */
    public static void sortAscii(String[] a) {
        // Используется объект ascii_comparer
        sort(a, null, 0, a.length-1, true, ascii_comparer);
    }

    /**
     *Часть массива строк ASCII сортируется по возрастанию или убыванию
     * в зависимости от аргумента up
     */
    public static void sortAscii(String[] a, int from, int to, boolean up) {
        // Используется объект ascii_comparer
        sort(a, null, from, to, up, ascii_comparer);
    }
/**
 * Массив строк ASCII сортируется по возрастанию без учёта регистра
 **/
    public static void sortAsciiIgnoreCase(String[] a) {
        sortAsciiIgnoreCase(a, 0, a.length-1, true);
    }
    /**
     *Часть массива строк ASCII сортируется по возрастанию или убыванию
     * без учёта регистра в зависимости от аргумента up
     */
    public static void sortAsciiIgnoreCase(String[] a, int from, int to, boolean up) {
        if ((a == null) || (a.length < 2)) return;
        // Создаётся вторичный массив строк, содержащий версию всех
        // строк оригинального массива в нижнем регистре.
        String b[] = new String[a.length];
        for(int i = 0; i < a.length; i++) b[i] = a[i].toLowerCase();
        // Сортируется этот вторичный массив, и изначальный массив
        // переупорядочивается точно в таком же порядке, в результате получается
        // сортировка, не зависящая от регистра.
        sort(b, a, from, to, up, ascii_comparer);
    }

    /**
     * Массив строк сортируется по возрастанию в соответствии
     * с принятым по умолчанию местным порядком символов
     **/
    public static void sort(String[] a) {
        sort(a, 0, a.length-1, true, false, null);
    }

    /**
     * Sort a portion of an array of strings, using the collation order of
     * the default locale.   If up is true, sort ascending, otherwise, sort
     * descending.  If ignorecase is true, ignore the capitalization of letters
     **/
    public static void sort(String[] a, int from, int to, boolean up, boolean ignorecase) {
        sort(a, from, to, up, ignorecase, null);
    }

    /**
     * Sort a portion of an array of strings, using the collation order of
     * the specified locale.   If up is true, sort ascending, otherwise, sort
     * descending.  If ignorecase is true, ignore the capitalization of letters
     **/
    public static void sort(String[] a, int from, int to, boolean up, boolean ignorecase, Locale locale) {
        // Don't sort if we don't have to
        if ((a == null) || (a.length < 2)) return;

        // The java.text.Collator object does internationalized string compares
        // Create one for the specified, or the default locale.
        Collator c;
        if (locale == null) c = Collator.getInstance();
        else c = Collator.getInstance(locale);

        // Specify whether or not case should be considered in the sort.
        // Note: this option does not seem to work correctly in JDK 1.1.1
        // using the default American English locale.
        if (ignorecase) c.setStrength(Collator.SECONDARY);

        // Use the Collator object to create an array of CollationKey objects
        // that correspond to each of the strings.
        // Comparing CollationKeys is much quicker than comparing Strings
        CollationKey[] b = new CollationKey[a.length];
        for(int i = 0; i < a.length; i++) b[i] = c.getCollationKey(a[i]);

        // Now define a Comparer object to compare collation keys, using an
        // anonymous class.
        Comparer comp =  new Comparer() {
            public int compare(Object a, Object b) {
                return ((CollationKey)a).compareTo((CollationKey)b);
            }
        };

        // Finally, sort the array of CollationKey objects, rearranging the
        // original array of strings in exactly the same way.
        sort(b, a, from, to, up, comp);
    }

    /** Sort an array of Comparable objects into ascending order */
    public static void sort(Comparable[] a) {
        sort(a, null, 0, a.length-1, true);
    }

    /**
     * Sort a portion of an array of Comparable objects.  If up is true,
     * sort into ascending order, otherwise sort into descending order.
     **/
    public static void sort(Comparable[] a, int from, int to, boolean up) {
        sort(a, null, from, to, up, comparable_comparer);
    }

    /**
     * Sort a portion of array a of Comparable objects.  If up is true,
     * sort into ascending order, otherwise sort into descending order.
     * Re-arrange the array b in exactly the same way as a.
     **/
    public static void sort(Comparable[] a, Object[] b,
                            int from, int to, boolean up) {
        sort(a, b, from, to, up, comparable_comparer);
    }

    /**
     * Sort an array of arbitrary objects into ascending order, using the
     * comparison defined by the Comparer object c
     **/
    public static void sort(Object[] a, Comparer c) {
        sort(a, null, 0, a.length-1, true, c);
    }

    /**
     * Sort a portion of an array of objects, using the comparison defined by
     * the Comparer object c.  If up is true, sort into ascending order,
     * otherwise sort into descending order.
     **/
    public static void sort(Object[] a, int from, int to, boolean up,
                            Comparer c)
    {
        sort(a, null, from, to, up, c);
    }

    /**
     * This is the main sort() routine. It performs a quicksort on the elements
     * of array a between the element from and the element to.  The up argument
     * specifies whether the elements should be sorted into ascending (true) or
     * descending (false) order.  The Comparer argument c is used to perform
     * comparisons between elements of the array.  The elements of the array b
     * are reordered in exactly the same way as the elements of array a are.
     **/
    public static void sort(Object[] a, Object[] b,
                            int from, int to,
                            boolean up, Comparer c)
    {
        // If there is nothing to sort, return
        if ((a == null) || (a.length < 2)) return;

        // This is the basic quicksort algorithm, stripped of frills that can
        // make it faster but even more confusing than it already is.  You
        // should understand what the code does, but don't have to understand
        // just why it is guaranteed to sort the array...
        // Note the use of the compare() method of the Comparer object.
        int i = from, j = to;
        Object center = a[(from + to) / 2];
        do {
            if (up) {  // an ascending sort
                while((i < to)&& (c.compare(center, a[i]) > 0)) i++;
                while((j > from)&& (c.compare(center, a[j]) < 0)) j--;
            } else {   // a descending sort
                while((i < to)&& (c.compare(center, a[i]) < 0)) i++;
                while((j > from)&& (c.compare(center, a[j]) > 0)) j--;
            }
            if (i < j) {
                Object tmp = a[i];  a[i] = a[j];  a[j] = tmp; // swap elements
                if (b != null) { tmp = b[i]; b[i] = b[j]; b[j] = tmp; } // swap
            }
            if (i <= j) { i++; j--; }
        } while(i <= j);
        if (from < j) sort(a, b, from, j, up, c); // recursively sort the rest
        if (i < to) sort(a, b, i, to, up, c);
    }

    /**
     * This nested class defines a test program that demonstrates several
     * ways to use the Sorter class to sort ComplexNumber objects
     **/
    public static class Test {
        /**
         * This subclass of ComplexNumber implements the Comparable interface
         * and defines a compareTo() method for comparing complex numbers.
         * It compares numbers based on their magnitude. I.e. on their distance
         * from the origin.
         **/
        static class SortableComplexNumber extends ComplexNumber
                implements Sorter.Comparable {
            public SortableComplexNumber(double x, double y) { super(x, y); }
            public int compareTo(Object other) {
                return sign(this.magnitude()-((ComplexNumber)other).magnitude());
            }
        }

        /** A a test program that sorts complex numbers in various ways. */
        public static void main(String[] args) {
            // Define an array of SortableComplexNumber objects.  Initialize it
            // to contain random complex numbers.
            SortableComplexNumber[] a = new SortableComplexNumber[5];
            for(int i = 0; i < a.length; i++)
                a[i] = new SortableComplexNumber(Math.random()*10,
                        Math.random()*10);

            // Now sort it using the SortableComplexNumber compareTo() method,
            // which sorts by magnitude, and print the results out.
            System.out.println("Sorted by magnitude:");
            Sorter.sort(a);
            for(int i = 0; i < a.length; i++) System.out.println(a[i]);

            // Sort the complex numbers again, using a Comparer object that
            // compares them based on the sum of their real and imaginary parts
            System.out.println("Sorted by sum of real and imaginary parts:");
            Sorter.sort(a, new Sorter.Comparer() {
                public int compare(Object a, Object b) {
                    ComplexNumber i = (ComplexNumber)a;
                    ComplexNumber j = (ComplexNumber)b;
                    return sign((i.real() + i.imaginary()) -
                            (j.real() + j.imaginary()));
                }
            });
            for(int i = 0; i < a.length; i++) System.out.println(a[i]);

            // Sort them again using a Comparer object that compares their real
            // parts, and then their imaginary parts
            System.out.println("Sorted descending by real, then imaginary:");
            Sorter.sort(a, 0, a.length-1, false, new Sorter.Comparer() {
                public int compare(Object a, Object b) {
                    ComplexNumber i = (ComplexNumber) a;
                    ComplexNumber j = (ComplexNumber) b;
                    double result = i.real() - j.real();
                    if (result == 0) result = i.imaginary()-j.imaginary();
                    return sign(result);
                }
            });
            for(int i = 0; i < a.length; i++) System.out.println(a[i]);
        }

        /** This is a convenience routine used by comparison routines */
        public static int sign(double x) {
            if (x > 0) return 1;
            else if (x < 0) return -1;
            else return 0;
        }
    }
}

