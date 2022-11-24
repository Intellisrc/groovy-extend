package com.intellisrc.groovy

import groovy.transform.CompileStatic

import java.math.RoundingMode

/**
 * @since 2022/11/09.
 */
@CompileStatic
class BigDecimalExt {
    static BigInteger ceil(BigDecimal self) {
        return self.setScale(0, RoundingMode.CEILING) as BigInteger
    }
    static BigDecimal ceil(BigDecimal self, int decimals) {
        return self.setScale(decimals, RoundingMode.CEILING) as BigDecimal
    }
    static BigInteger floor(BigDecimal self) {
        return self.setScale(0, RoundingMode.FLOOR) as BigInteger
    }
    static BigDecimal floor(BigDecimal self, int decimals) {
        return self.setScale(decimals, RoundingMode.FLOOR) as BigDecimal
    }
}
