package com.wsi.model;

import java.util.Objects;

/**
 *  Zip Code Range contains properties such as from, to which also being validated once we set the data.
 *
 * @author knguyen93
 */
public class ZipCodeRange {
    public static final String INVALID_ZIP_CODE_RANGE = "Invalid Zip Code Range";
    public static final int ZIP_CODE_MINIMUM = 10000;
    public static final int ZIP_CODE_MAXIMUM = 99999;

    private int from;
    private int to;

    public ZipCodeRange(int from, int to) {
        if (!isValid(from) || !isValid(to) || from > to) throw new IllegalArgumentException(INVALID_ZIP_CODE_RANGE);
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    /**
     * Convenience method to validate Zip Code, currently supports Zip Code with 5-digits format
     *
     * @param zipCode a integer Zip Code
     * @return whether the Zip Code is valid
     */
    private boolean isValid(int zipCode) {
        return zipCode >= ZIP_CODE_MINIMUM && zipCode <= ZIP_CODE_MAXIMUM;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZipCodeRange that = (ZipCodeRange) o;
        return from == that.from &&
            to == that.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "ZipCodeRange{" +
            "from=" + from +
            ", to=" + to +
            '}';
    }
}
