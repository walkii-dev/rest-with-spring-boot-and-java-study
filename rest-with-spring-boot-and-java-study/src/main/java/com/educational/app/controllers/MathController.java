package com.educational.app.controllers;

import com.educational.app.exceptions.ResourceNotFoundException;
import com.educational.app.services.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    private MathService service;

    //http://localhost:8080/math/sum
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum (
            @PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        service.mathVerifyTwoValues(numberOne,numberTwo);
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    //http://localhost:8080/math/subtraction
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction (
            @PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        service.mathVerifyTwoValues(numberOne,numberTwo);
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    //http://localhost:8080/math/division
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division (
            @PathVariable("numberOne")String numberOne, @PathVariable("numberTwo")String numberTwo){
        service.mathVerifyTwoValues(numberOne,numberTwo);
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    //http://localhost:8080/math/multiplication
    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication (
            @PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        service.mathVerifyTwoValues(numberOne,numberTwo);
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    //http://localhost:8080/math/mean
    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean (@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        service.mathVerifyTwoValues(numberOne,numberTwo);

        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    //http://localhost:8080/math/squareRoot
    @RequestMapping("/squareroot/{number}")
    public Double squareRoot (
            @PathVariable("number")String number){
        service.mathVerifyOneValue(number);
        return Math.sqrt(convertToDouble(number));
    }

    private Double convertToDouble(String strNumber) throws ResourceNotFoundException{
        if (strNumber == null || strNumber.isEmpty()) throw new ResourceNotFoundException("field is empty or null");
        String number = strNumber.replace(",",".");
        return Double.parseDouble(number);
    }

}