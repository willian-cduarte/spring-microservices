package br.com.will.startup.math;

import br.com.will.startup.exceptions.UnsupportedMathOperationException;
import br.com.will.startup.math.converters.NumberConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class MathController {

    @Autowired
    private final SimpleMath simpleMath;

    public MathController(SimpleMath simpleMath) {
        this.simpleMath = simpleMath;
    }

    @RequestMapping(
            value = "/sum/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return simpleMath.sum(
                NumberConverter.convertToDouble(numberOne),
                NumberConverter.convertToDouble(numberTwo)
        );
    }

    @RequestMapping(
            value = "/subtraction/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double subtraction(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return simpleMath.subtraction(
                NumberConverter.convertToDouble(numberOne),
                NumberConverter.convertToDouble(numberTwo)
        );
    }

    @RequestMapping(
            value = "/multiplication/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double multiplication(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return simpleMath.multiplication(
                NumberConverter.convertToDouble(numberOne),
                NumberConverter.convertToDouble(numberTwo)
        );
    }

    @RequestMapping(
            value = "/division/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double division(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return simpleMath.division(
                NumberConverter.convertToDouble(numberOne),
                NumberConverter.convertToDouble(numberTwo)
        );
    }

    @RequestMapping(
            value = "/average/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double average(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return simpleMath.average(
                NumberConverter.convertToDouble(numberOne),
                NumberConverter.convertToDouble(numberTwo)
        );
    }

    @RequestMapping(
            value = "/square-root/{number}",
            method = RequestMethod.GET
    )
    public Double squareRoot(
            @PathVariable(value = "number") String number) throws Exception {
        if (!NumberConverter.isNumeric(number))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return simpleMath.squareRoot(NumberConverter.convertToDouble(number));
    }


}
