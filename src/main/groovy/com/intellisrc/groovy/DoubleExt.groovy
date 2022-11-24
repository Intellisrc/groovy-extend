package com.intellisrc.groovy

import groovy.transform.CompileStatic

/**
 * @since 2022/11/09.
 */
@CompileStatic
class DoubleExt {
    static long ceil(Double self) {
        return Math.ceil(self) as int
    }
    static double ceil(Double self, int decimals) {
        return Math.ceil(self * Math.pow(10, decimals)) / Math.pow(10, decimals) as double
    }
    static long floor(Double self) {
        return Math.floor(self) as int
    }
    static double floor(Double self, int decimals) {
        return Math.floor(self * Math.pow(10, decimals)) / Math.pow(10, decimals) as double
    }
}
