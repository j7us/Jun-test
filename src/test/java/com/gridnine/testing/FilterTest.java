package com.gridnine.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void filtertest(List<Flight> flights, Predicate...predicates){
        Filter.filter(flights,predicates);
        assertEquals("Не задан полет или фильтр\n", outContent.toString());
    }

    private static Stream<Arguments> provideParameters(){
        List<Flight> flights = new ArrayList<>();
        Predicate[]predicates = new Predicate[1];
        return Stream.of(
                Arguments.of(null,null),
                Arguments.of(flights,predicates)
        );
    }

}
