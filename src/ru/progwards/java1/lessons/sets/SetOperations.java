package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {
    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2){
        HashSet result = new HashSet(set1);
        result.addAll(set2);
        return result;
    }

    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2){
        HashSet result = new HashSet(set1);
        result.retainAll(set2);
        return result;
    }

    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2){
        HashSet unionSet = new HashSet(union(set1, set2));
        unionSet.removeAll(set2);
        return unionSet;
    }

    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2){
        HashSet unionSet = new HashSet(union(set1, set2));
        HashSet internSet = new HashSet(intersection(set1, set2));
        unionSet.removeAll(internSet);
        return unionSet;
    }
}
