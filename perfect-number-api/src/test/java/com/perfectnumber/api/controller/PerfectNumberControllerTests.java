package com.perfectnumber.api.controller;

import com.perfectnumber.api.PerfectNumberApplication;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PerfectNumberApplication.class)
@AutoConfigureMockMvc
public class PerfectNumberControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testIsPerfectNumber() throws Exception {
        this.mockMvc.perform(get("/perfect-number/6"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("responseCode", is("OK")))
                .andExpect(jsonPath("message", is(true)));

        this.mockMvc.perform(get("/perfect-number/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("responseCode", is("OK")))
                .andExpect(jsonPath("message", is(false)));
    }

    @Test
    void testGetPerfectNumbersInRange() throws Exception {
        List<Long> expectedReturn = Arrays.asList(6L, 28L, 496L, 8128L);

        JSONArray jsonArray = new JSONArray();
        org.json.JSONArray jsonArrayTemp = new org.json.JSONArray(expectedReturn.toString());
        for (int index = 0; index < jsonArrayTemp.length(); index++) {
            jsonArray.add(jsonArrayTemp.get(index));
        }

        this.mockMvc.perform(get("/perfect-number/in-range")
                        .param("lowerBoundValue", "1")
                        .param("upperBoundValue", "10000"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("responseCode", is("OK")))
                .andExpect(jsonPath("message", is(jsonArray)));
    }
}
