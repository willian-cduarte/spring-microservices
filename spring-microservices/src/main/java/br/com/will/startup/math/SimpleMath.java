package br.com.will.startup.math;

import br.com.will.startup.exceptions.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class SimpleMath {

    public Double sum(Double numberOne, Double numberTwo) throws Exception {
        return Double.sum(numberOne, numberTwo);
    }

    public Double subtraction(Double numberOne, Double numberTwo) throws Exception {
        return numberOne - numberTwo;
    }

    public Double multiplication(Double numberOne, Double numberTwo) throws Exception {
        return numberOne * numberTwo;
    }

    public Double division(Double numberOne, Double numberTwo) throws Exception {
        return numberOne / numberTwo;
    }

    public Double average(Double numberOne, Double numberTwo) throws Exception {
        return Stream.of(numberOne, numberTwo)
                .mapToDouble(a -> a)
                .average().orElseThrow(() -> new UnsupportedMathOperationException("Please set a numeric value"));
    }

    public Double squareRoot(Double number) throws Exception {
        return Math.sqrt(number);
    }
}
