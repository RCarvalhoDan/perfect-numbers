package com.perfectnumber.api.service;

import com.perfectnumber.api.utils.PerfectNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PerfectNumberServiceTests {

    private final PerfectNumberService perfectNumberService = new PerfectNumberServiceImpl();

    @Test
    void testIsPerfectNumber() throws PerfectNumberException {
        assertTrue(perfectNumberService.isPerfectNumber(6L));
        assertTrue(perfectNumberService.isPerfectNumber(28L));
        assertFalse(perfectNumberService.isPerfectNumber(10L));
    }

    @Test
    void testThrowsWhenOutOfBounds() {
        assertThrows(PerfectNumberException.class, () -> perfectNumberService.isPerfectNumber(-6L));
        assertThrows(PerfectNumberException.class, () -> perfectNumberService.isPerfectNumber(0L));
    }

    @Test
    void testGetPerfectNumbersInRange() throws PerfectNumberException {
        List<Long> result = perfectNumberService.findAllInRange(1L, 10000L);
        List<Long> expected = List.of(6L, 28L, 496L, 8128L);
        assertEquals(expected, result);
    }
}
