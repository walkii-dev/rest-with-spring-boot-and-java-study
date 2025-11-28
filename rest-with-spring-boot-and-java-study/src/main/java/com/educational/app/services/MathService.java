package com.educational.app.services;

import com.educational.app.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MathService {

    public void mathVerifyTwoValues (String arg1, String arg2) throws ResourceNotFoundException{
        if (!isNumeric(arg1) || !isNumeric(arg2)) throw new ResourceNotFoundException("please input numbers on parameters.");
    }
    public void mathVerifyOneValue (String arg) throws ResourceNotFoundException{
        if (!isNumeric(arg)) throw new ResourceNotFoundException("please input numbers on parameters.");
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replace(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }



}
