package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ivan", "Peter", "John", "Mary", "Kate");

        String result = IntStream.range(0, names.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> String.format("%d. %s", i + 1, names.get(i)))
                .collect(Collectors.joining(", "));

        System.out.println(result); 
    }
}