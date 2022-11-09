package com.intellisrc.groovy

import groovy.transform.CompileStatic

/**
 * @since 2022/11/09.
 */
@CompileStatic
class FloatExt {
    static int ceil(Float self) {
        return Math.ceil(self) as int
    }
    static float ceil(Float self, int decimals) {
        return Math.ceil(self * Math.pow(10, decimals)) / Math.pow(10, decimals) as float
    }
    static int floor(Float self) {
        return Math.floor(self) as int
    }
    static float floor(Float self, int decimals) {
        return Math.floor(self * Math.pow(10, decimals)) / Math.pow(10, decimals) as float
    }
}
