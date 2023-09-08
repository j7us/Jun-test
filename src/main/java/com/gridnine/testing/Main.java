package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        Predicate<Flight> predicate1 = x -> x.getSegments().get(0).getDepartureDate().isAfter(LocalDateTime.now());
        Filter.filter(flights, predicate1 );
        Predicate<Flight> predicate2 = x -> x.getSegments().stream()
                .filter(i -> i.getArrivalDate().isBefore(i.getDepartureDate()))
                .count()==0;
        System.out.println();
        Filter.filter(flights,predicate2);
        Predicate<Flight> predicate3= x -> x.getSegments().size()==1;
        Predicate<Flight> predicate4 = x -> IntStream.range(0, x.getSegments().size()-1)
                                                      .mapToObj(i -> Duration.between(x.getSegments().get(i).getArrivalDate(),
                                                                x.getSegments().get(i+1).getDepartureDate()))
                                                      .mapToLong(i -> i.toHours()).sum()<=2;
        System.out.println();
        Filter.filter(flights,predicate3.or(predicate4));


    }
}
