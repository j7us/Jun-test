package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Filter {

    public static void filter(List<Flight> flights, Predicate...predicates){
        if (flights==null || predicates==null || flights.isEmpty() || predicates.length==0){
            System.out.println("Не задан полет или фильтр");
            return;
        }
        Predicate<Flight> fin = predicates[0];
        for (int i = 1; i < predicates.length; i++) {
            fin = fin.and(predicates[i]);
        }
        flights.stream().filter(fin).forEach(x -> System.out.println(x.toString()));
    }
}
