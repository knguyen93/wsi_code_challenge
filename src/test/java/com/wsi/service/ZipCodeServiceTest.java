package com.wsi.service;

import com.wsi.model.ZipCodeRange;
import org.junit.Before;
import org.junit.Test;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.Assert.*;

/**
 * @author knguyen93
 */
public class ZipCodeServiceTest {
    private ZipCodeService zipCodeService;

    @Before
    public void setup() {
        zipCodeService = new ZipCodeServiceImpl();
    }

    @Test
    public void test_mergeZipCodesRangesNoOverlap() {
        int[][] zipCodesRanges = {{94133, 94133}, {94133, 94133}, {94133, 94133}, {94200, 94299}, {94600, 94699}};
        int[][] expectedRanges = {{94133, 94133}, {94200, 94299}, {94600, 94699}};

        List<ZipCodeRange> results = zipCodeService.mergeZipCodesRanges(zipCodesRanges);
        List<ZipCodeRange> expected = parseZipCodesRange(expectedRanges);
        results.sort(zipCodeRangeComparator());
        expected.sort(zipCodeRangeComparator());

        assertEquals(expected, results);
    }

    @Test
    public void test_mergeZipCodesRangesWithOverlap() {
        int[][] zipCodesRanges = {{49679, 52015}, {49800, 50000}, {51500, 53479}, {45012, 46937},
            {54012, 59607}, {45500, 45590}, {45999, 47900}, {44000, 45000}, {43012, 45950}};
        int[][] expectedRanges = {{43012, 47900}, {49679, 53479}, {54012, 59607}};

        List<ZipCodeRange> results = zipCodeService.mergeZipCodesRanges(zipCodesRanges);
        List<ZipCodeRange> expected = parseZipCodesRange(expectedRanges);

        results.sort(zipCodeRangeComparator());
        expected.sort(zipCodeRangeComparator());

        assertEquals(expected, results);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_mergeZipCodesRanges_withNullInput() {
        zipCodeService.mergeZipCodesRanges(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_mergeZipCodesRanges_invalid_withFromGreaterThanTo() {
        int[][] zipCodesRanges = {{94299, 94200}};
        zipCodeService.mergeZipCodesRanges(zipCodesRanges);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_mergeZipCodesRanges_invalid_withMissingProperty() {
        int[][] zipCodesRanges = {{94133, 94133}, {94299}};
        zipCodeService.mergeZipCodesRanges(zipCodesRanges);
    }

    private List<ZipCodeRange> parseZipCodesRange(int[][] zipCodeRanges) {
        return Stream.of(zipCodeRanges)
            .map(zip -> new ZipCodeRange(zip[0], zip[1]))
            .sorted(zipCodeRangeComparator())
            .collect(Collectors.toList());
    }

    private Comparator<ZipCodeRange> zipCodeRangeComparator() {
        return Comparator.comparingInt(ZipCodeRange::getFrom);
    }
}