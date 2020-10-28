package com.wsi.service;

import com.wsi.model.ZipCodeRange;
import java.util.List;

/**
 * Zip Code Service that handle some operation related to Zip Code such as merging overlap Zip Code Ranges
 *
 * @author knguyen93
 */
public interface ZipCodeService {

    /**
     * Merge Zip Code Ranges into smaller set of ranges by merging overlap Zip Code Ranges
     *
     * @param zipCodeRanges Zip Code Ranges as 2D array
     *
     * @return the merged list of Zip Code Ranges
     */
    List<ZipCodeRange> mergeZipCodesRanges(int[][] zipCodeRanges);
}
