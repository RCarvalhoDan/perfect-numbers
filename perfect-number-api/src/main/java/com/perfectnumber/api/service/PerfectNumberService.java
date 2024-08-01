package com.perfectnumber.api.service;

import com.perfectnumber.api.utils.PerfectNumberException;

import java.util.List;

public interface PerfectNumberService {
    public Boolean isPerfectNumber(Long number) throws PerfectNumberException;

    public List<Long> findAllInRange(Long lowerBound, Long upperBound) throws PerfectNumberException;
}
