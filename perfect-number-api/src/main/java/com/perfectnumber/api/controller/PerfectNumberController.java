package com.perfectnumber.api.controller;


import com.perfectnumber.api.utils.PerfectNumberException;
import com.perfectnumber.api.service.PerfectNumberService;
import com.perfectnumber.api.utils.Response;
import com.perfectnumber.api.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/perfect-number")
public class PerfectNumberController {

    @Autowired
    private PerfectNumberService perfectNumberService;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{numberValue}")
    public Response<?> isPerfectNumber(@PathVariable String numberValue) {
        Response<?> response;
        try {
            Long number = Long.valueOf(numberValue);
            Response<Boolean> okResponse = new Response<Boolean>();
            okResponse.setMessage(perfectNumberService.isPerfectNumber(number));
            okResponse.setResponseCode(ResponseCode.OK);
            response = okResponse;
        } catch (Exception e) {
            response = handleException(e);
        }
        return response;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/in-range")
    public Response<?> findAllInRange(@RequestParam String lowerBoundValue, @RequestParam String upperBoundValue) {
        Response<?> response;

        try {
            Long lowerBound = Long.valueOf(lowerBoundValue);
            Long upperBound = Long.valueOf(upperBoundValue);
            Response<List<Long>> okResponse = new Response<>();
            okResponse.setMessage(perfectNumberService.findAllInRange(lowerBound, upperBound));
            okResponse.setResponseCode(ResponseCode.OK);
            response = okResponse;
        } catch (Exception e) {
            response = handleException(e);
        }
        return response;
    }

    private Response<?> handleException(Exception e) {
        Response<?> response;
        Response<String> notOkResponse = new Response<String>();
        if (e instanceof PerfectNumberException) {
            notOkResponse.setMessage(e.getMessage());
            notOkResponse.setResponseCode(ResponseCode.REQUEST_ERROR);
        } else if (e instanceof NumberFormatException) {
            notOkResponse.setMessage("The input parameter(s) must be an integer between 1 and " + Long.MAX_VALUE + ".");
            notOkResponse.setResponseCode(ResponseCode.REQUEST_ERROR);
        } else {
            notOkResponse.setMessage("There is some internal problems. Please try later.");
            notOkResponse.setResponseCode(ResponseCode.INTERNAL_ERROR);
        }
        response = notOkResponse;
        return response;
    }
}
