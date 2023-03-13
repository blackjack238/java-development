package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream<String> names =
                Arrays.asList("1. John", "2. Bill", "3. Max", "4. Alex")
                        .stream()
                        .filter(name -> Character.getNumericValue(name.charAt(1))%2!=0)
                        .map(name -> "Mr/Ms " + name)
                        .sorted()
                        .limit(2);;
        List<String> filteredNames = names.collect(Collectors.toList());

        System.out.println(filteredNames);
    }
}