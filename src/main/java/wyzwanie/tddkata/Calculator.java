package wyzwanie.tddkata;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Calculator {

    private static final String DEFAULT_SEPARATOR = ",";


    public Integer add(String input) throws NegativeNotAllowed {
        int sum =0;
        if (input != null) {
            String[] inputExtractDelimiter = input.split("\\\\n");
            String separator = DEFAULT_SEPARATOR;
            String computedInput = input;
            if(inputExtractDelimiter.length == 2) {
                int firstDelimiterOccurrence = inputExtractDelimiter[0].indexOf("[");
                int secondDelimiterOccurrence = inputExtractDelimiter[0].indexOf("]");
                separator = inputExtractDelimiter[0].substring(firstDelimiterOccurrence + 1, secondDelimiterOccurrence);
                computedInput = inputExtractDelimiter[1];
                if (separator.isEmpty()) {
                    separator = DEFAULT_SEPARATOR;
                }
            }
            sum = countSum(computedInput,separator);
        }
        return sum;
    }

    private Integer countSum(String inputNumbers, String separator) throws NegativeNotAllowed {
        String[] inputToArray = inputNumbers.split(separator);
        ArrayList<Integer>  inputToIntArray = new ArrayList<>();
        ArrayList<Integer> negativeInputIntArray = new ArrayList<>();
        for (int i = 0; i < inputToArray.length; i++) {
            try {
                int intValue = Integer.parseInt(inputToArray[i].trim());

                if(intValue > 0 && intValue < 1001){
                    inputToIntArray.add(intValue);
                }
                if(intValue< 0) {
                    negativeInputIntArray.add(intValue);
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a number");
            }
        }
        if(!negativeInputIntArray.isEmpty()){
            throw new NegativeNotAllowed("NegativeNotAllowed" + negativeInputIntArray.toString().replace("[","(").replace("]",")"));
        }
//        return IntStream.of(inputToIntArray).sum();
        return  inputToIntArray.stream().mapToInt(Integer::intValue).sum();
    }

    public class NegativeNotAllowed extends Exception {
        public NegativeNotAllowed(String errorMessage) {
            super(errorMessage);
        }
    }

    //Do not modify code below this line. This is just a runner

    public static void main(String[] args) throws NegativeNotAllowed {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter calculation. Ctrl+d for exit.");

        Calculator calculator = new Calculator();
        System.out.print("> ");
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(input + " ==> " + calculator.add(input));

            System.out.print("> ");
        }

    }
}
