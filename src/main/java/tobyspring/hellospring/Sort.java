package tobyspring.hellospring;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
    public static void main(String[] args) {
        List<Integer> scores = Arrays.asList(5, 7, 1, 9, 2, 8, 3);
        Collections.sort(scores);

        List<String> others = Arrays.asList("z", "x", "spring", "java");

        others.sort(Comparator.comparingInt(String::length));
        // Collections.sort(others, Comparator.comparingInt(String::length));
        scores.forEach(System.out::println);
        others.forEach(System.out::println);
    }
}
