package com.wsi.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author knguyen93
 */
public class ZipCodeRangeTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidZipCodeData_RangeExceed() {
        Random random = new Random();
        int from = ZipCodeRange.ZIP_CODE_MAXIMUM + random.nextInt(1000);
        int to = ZipCodeRange.ZIP_CODE_MAXIMUM + random.nextInt(1000);
        new ZipCodeRange(from, to);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidZipCodeData_ZipCodeToLessThanZipCodeFrom() {
        Random random = new Random();
        int from = ZipCodeRange.ZIP_CODE_MAXIMUM + random.nextInt(1000);
        int to = from - 1;
        new ZipCodeRange(from, to);
    }

    @Test
    public void testEquals() {
        Random random = new Random();
        int from = ZipCodeRange.ZIP_CODE_MINIMUM + random.nextInt(8000);
        int to = from + 1;
        ZipCodeRange zipCodeRange = new ZipCodeRange(from, to);
        ZipCodeRange zipCodeRange2 = new ZipCodeRange(from, to);
        assertEquals(zipCodeRange, zipCodeRange2);
    }

    @Test
    public void testHashCode() {
        Random random = new Random();
        int from = ZipCodeRange.ZIP_CODE_MINIMUM + random.nextInt(8000);
        int to = from + 1;
        ZipCodeRange zipCodeRange = new ZipCodeRange(from, to);
        ZipCodeRange zipCodeRange2 = new ZipCodeRange(from, to);
        boolean isValid = zipCodeRange.equals(zipCodeRange2) && zipCodeRange.hashCode() == zipCodeRange2.hashCode();
        assertTrue(isValid);
    }
}