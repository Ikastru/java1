package Allen_Douny;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jfree.data.xy.XYSeries;


public class ProfileListAdd {

    public static void main(String[] args) {
        profileArrayListAddEnd();
        profileArrayListAddBeginning();
        profileLinkedListAddBeginning();
        profileLinkedListAddEnd();
    }

    /**
     * Characterize the run time of adding to the end of an ArrayList
     */
    public static void profileArrayListAddEnd() {
        Profiler.Timeable timeable = new Profiler.Timeable() {
            List<String> list;

            public void setup(int n) {
                list = new ArrayList<String>();
            }

            public void timeMe(int n) {
                for (int i=0; i<n; i++) {
                    list.add("a string");
                }
            }
        };
        int startN = 4000;
        int endMillis = 1000;
        runProfiler("ArrayList add end", timeable, startN, endMillis);
    }

    /**
     * Characterize the run time of adding to the beginning of an ArrayList
     */
    public static void profileArrayListAddBeginning() {
        Profiler.Timeable timeable = new Profiler.Timeable() {
            List<String> list;

            public void setup(int n) {
                list = new ArrayList<String>();
            }

            public void timeMe(int n) {
                for (int i=0; i<n; i++) {
                    list.add(0, "a string");
                }
            }
        };
        int startN = 4000;
        int endMillis = 1000;
        runProfiler("ArrayList add end", timeable, startN, endMillis);
    }

    /**
     * Characterize the run time of adding to the beginning of a LinkedList
     */
    public static void profileLinkedListAddBeginning() {
        Profiler.Timeable timeable = new Profiler.Timeable() {
            List<String> list;

            public void setup(int n) {
                list = new LinkedList<>();
            }

            public void timeMe(int n) {
                for (int i=0; i<n; i++) {
                    list.add(0, "a string");
                }
            }
        };
        int startN = 4000;
        int endMillis = 1000;
        runProfiler("ArrayList add end", timeable, startN, endMillis);
    }

    /**
     * Characterize the run time of adding to the end of a LinkedList
     */
    public static void profileLinkedListAddEnd() {
        Profiler.Timeable timeable = new Profiler.Timeable() {
            List<String> list;

            public void setup(int n) {
                list = new LinkedList<>();
            }

            public void timeMe(int n) {
                for (int i=0; i<n; i++) {
                    list.add("a string");
                }
            }
        };
        int startN = 4000;
        int endMillis = 1000;
        runProfiler("ArrayList add end", timeable, startN, endMillis);
    }

    /**
     * Runs the profiles and displays results.
     *
     * @param timeable
     * @param startN
     * @param endMillis
     */
    private static void runProfiler(String title, Profiler.Timeable timeable, int startN, int endMillis) {
        Profiler profiler = new Profiler(title, timeable);
        XYSeries series = profiler.timingLoop(startN, endMillis);
        profiler.plotResults(series);
    }
}
