package org.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class task2 {
    public static void main(String[] args) {
        Stream<String> names =
                Arrays.asList("John", "Bill", "Max", "Alex")
                        .stream()
                        .sorted();

        List<String> filteredNames = names.collect(Collectors.toList());
        Collections.reverse(filteredNames);
        System.out.println(filteredNames);
    }
}
