package com.wsi.service;

import com.wsi.model.ZipCodeRange;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementations of {@link ZipCodeService} that implement various useful operations, such as merge overlap Zip Code
 * Range into smaller list of Zip Code Range to reduce redundancy
 *
 * @author knguyen93
 */
public class ZipCodeServiceImpl implements ZipCodeService {

    public static final String ZIP_CODE_RANGE_REQUIRED_TWO_NUMBERS = "ZipCodeRange required two numbers";

    /**
     * {@link ZipCodeService#mergeZipCodesRanges}
     */
    @Override
    public List<ZipCodeRange> mergeZipCodesRanges(int[][] zipCodeRanges) {
        Deque<ZipCodeRange> stack = new ArrayDeque<>();
        parseZipCodesRange(zipCodeRanges)
            .stream()
            .sorted(Comparator.comparingInt(ZipCodeRange::getFrom))
            .forEach(currentZipRange -> {
                if (stack.isEmpty()) {
                    stack.addFirst(currentZipRange);
                } else {
                    ZipCodeRange zipCodeRange = stack.peekFirst();

                    // not overlap
                    if (currentZipRange.getFrom() > zipCodeRange.getTo()) {
                        stack.addFirst(currentZipRange);
                    } else if (zipCodeRange.getTo() < currentZipRange.getTo()) {
                        // merge in case two ranges are overlap
                        ZipCodeRange updatedRange = new ZipCodeRange(zipCodeRange.getFrom(), currentZipRange.getTo());
                        stack.pollFirst();
                        stack.addFirst(updatedRange);
                    }
                }
            });

        return new ArrayList<>(stack);
    }

    /**
     * Convenience method to parse the 2D array of Zip Code Ranges into List of Zip Code Ranges
     *
     * @param zipCodeRanges 2D array of Zip Code Ranges
     * @return List of ZipCodeRange
     */
    private List<ZipCodeRange> parseZipCodesRange(int[][] zipCodeRanges) {
        if (zipCodeRanges == null) throw new IllegalArgumentException();

        return Stream.of(zipCodeRanges)
            .map(zip -> {
                if (zip.length < 2) throw new IllegalArgumentException(ZIP_CODE_RANGE_REQUIRED_TWO_NUMBERS);
                return new ZipCodeRange(zip[0], zip[1]);
            })
            .collect(Collectors.toList());
    }
}
