package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class task3 {public static void main(String[] args) {
    String[] arr = {"1, 2, 0", "4, 5"};

    String result = Arrays.stream(arr)
            .flatMap(s -> Arrays.stream(s.split(", ")))
            .map(Integer::parseInt)
            .sorted()
            .map(String::valueOf)
            .collect(Collectors.joining(", "));



    System.out.println(result);
}
}
