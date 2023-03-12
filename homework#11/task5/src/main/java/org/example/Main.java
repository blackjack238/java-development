package org.example;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("a", "b", "c", "d");
        Stream<String> stream2 = Stream.of("1", "2", "3");

        Stream<String> zippedStream = zip(stream1, stream2);
        zippedStream.forEach(System.out::println);
    }
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();

        return Stream.generate(() -> {
            if (iterator1.hasNext() && iterator2.hasNext()) {
                return Stream.of(iterator1.next(), iterator2.next());
            }
            return null;
        }).takeWhile(Objects::nonNull).flatMap(Function.identity());
    }

}