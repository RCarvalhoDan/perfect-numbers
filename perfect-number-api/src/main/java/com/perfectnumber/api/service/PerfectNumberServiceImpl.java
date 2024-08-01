package com.perfectnumber.api.service;

import com.perfectnumber.api.utils.PerfectNumberException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class PerfectNumberServiceImpl implements PerfectNumberService {
    private final ArrayList<Long> allPerfectNumbers;

    public PerfectNumberServiceImpl() {
        this.allPerfectNumbers = findPerfectNumbers(Long.MAX_VALUE);
    }

    @Override
    public Boolean isPerfectNumber(Long number) throws PerfectNumberException {
        if (number<=0) {
            throw new PerfectNumberException("The number must be an integer between 1 and " + Long.MAX_VALUE + ".");
        }
        return this.allPerfectNumbers.contains(number);
    }

    @Override
    public List<Long> findAllInRange(Long lowerBoundValue, Long upperBoundValue) throws PerfectNumberException {
        if (lowerBoundValue<=0 || upperBoundValue<=0) {
            throw new PerfectNumberException("The lower and upper bound must be an integer between 1 and " + Long.MAX_VALUE + ".");
        } else if (lowerBoundValue>upperBoundValue) {
            throw new PerfectNumberException("The lower bound must be lesser than the upper bound.");
        }
        ArrayList<Long> perfectNumbers = new ArrayList<>();
        for (Long perfectNumber : this.allPerfectNumbers) {
            if (perfectNumber >= lowerBoundValue && perfectNumber <= upperBoundValue) {
                perfectNumbers.add(perfectNumber);
            }
        }
        return perfectNumbers;
    }

    private ArrayList<Long> findPerfectNumbers(Long upperBound) {
        ArrayList<Long> perfectNumbers = new ArrayList<>();

        for (int number = 2;; number++) {
            Long possiblePerfectNumber = (long) (Math.pow(2, number-1) * (Math.pow(2, number) - 1));

            if (possiblePerfectNumber < upperBound) {
                if (isPrime(number) && isPrime((int) (Math.pow(2, number) - 1))) {
                    perfectNumbers.add(possiblePerfectNumber);
                }
            } else {
                break;
            }
        }
        return perfectNumbers;
    }

    private Boolean isPrime(int number) {
        Boolean isPrime = true;

        if (number > 2) {
            for (int divisor = 2; divisor <= Math.sqrt(number)+1; divisor++) {
                if (Math.floorMod(number, divisor)==0) {
                    isPrime = false;
                    break;
                }
            }
        }
        return isPrime;
    }
}
