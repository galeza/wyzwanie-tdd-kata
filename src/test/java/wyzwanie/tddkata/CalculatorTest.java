package wyzwanie.tddkata;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.equalTo;


public class CalculatorTest {


    @ParameterizedTest
    @MethodSource
    public void should_sum_given_numbers(String input, Integer expectedResult) throws Calculator.NegativeNotAllowed {
        //given
        Calculator calculatorUnderTest = new Calculator();

        //when
        Integer result = calculatorUnderTest.add(input);

        //then
        assertThat(result, equalTo(expectedResult));

    }

    private static Stream<Arguments> should_sum_given_numbers() {
        return Stream.of(
                Arguments.of("", 0),
                Arguments.of(null, 0),
                Arguments.of(",", 0),
                Arguments.of("aga, 1", 1),
                Arguments.of("3", 3),
                Arguments.of("1,4", 5),
                Arguments.of("1, 4, 6, 2, 3, 4", 20),
                Arguments.of("//[,]\\n1,2", 3),
                Arguments.of("//[a]\\n1a2", 3),
                Arguments.of("//[]\\n1,2", 3),
                Arguments.of("2000,2,500", 502),
                Arguments.of("2000,2300,5500", 0),
                Arguments.of("1000,2300,1", 1001),
                Arguments.of("1001,2300,1", 1),
                Arguments.of("//[\\n]\\n1\\n2", 0)


        );
    }

    @ParameterizedTest
    @MethodSource
    public void should_return_exception_when_negative_number_provided(String input, String expectedResult) throws Calculator.NegativeNotAllowed {
        //given
        Calculator calculatorUnderTest = new Calculator();

        //when
        Exception exception = assertThrows(Calculator.NegativeNotAllowed.class,
                () -> calculatorUnderTest.add(input));

        //then
        assertThat(exception.getMessage(), equalTo(expectedResult));

    }

    private static Stream<Arguments> should_return_exception_when_negative_number_provided() {
        return Stream.of(
                Arguments.of("2,3,-1,6,-4", "NegativeNotAllowed(-1, -4)"),
                Arguments.of("-2,-3,-1,-6,-4", "NegativeNotAllowed(-2, -3, -1, -6, -4)")


        );
    }

}