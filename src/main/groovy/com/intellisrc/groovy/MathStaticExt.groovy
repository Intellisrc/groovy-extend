package com.intellisrc.groovy

import groovy.transform.CompileStatic

/**
 * This class try to address these issues:
 *
 * * In Groovy, when using @CompileStatic, round causes issues
 *   if all is not explicitly casted as double
 *
 * * It is very useful if floor and ceil return long instead of 'double'
 *
 * * Adds easy way to round/ceil/floor to a specific number of decimals
 *
 * WARNING: When using BigDecimal loss of precision may happen
 *
 * @since 2022/11/08.
 */
@CompileStatic
class MathStaticExt {
    /**
     * @param toRound
     * @return
     */
    static long roundNumber(Number toRound) {
        return Math.round(toRound.doubleValue())
    }
    /**
     * @param toRound
     * @param decimals : number of decimals to cut to
     * @return
     */
    static double roundNumber(Number toRound, int decimals) {
        return Math.round(toRound.doubleValue() * Math.pow(10d, decimals)) / Math.pow(10d, decimals)
    }
    /**
     * @param toRound
     * @return
     */
    static long ceilNumber(Number toRound) {
        return Math.ceil(toRound.doubleValue())
    }
    /**
     * @param toRound
     * @param decimals : number of decimals to cut to
     * @return
     */
    static double ceilNumber(Number toRound, int decimals) {
        return Math.ceil(toRound.doubleValue() * Math.pow(10d, decimals)) / Math.pow(10d, decimals)
    }
    /**
     * @param toRound
     * @return
     */
    static long floorNumber(Number toRound) {
        return Math.floor(toRound.doubleValue())
    }
    /**
     * @param toRound
     * @param decimals : number of decimals to cut to
     * @return
     */
    static double floorNumber(Number toRound, int decimals) {
        return Math.floor(toRound.doubleValue() * Math.pow(10d, decimals)) / Math.pow(10d, decimals)
    }
}
