package com.intellisrc.groovy

import groovy.transform.CompileStatic

/**
 * Created by lepe on 17/02/22.
 */
@CompileStatic
class RandomStaticExt {
    /**
     * Generates a random number between min and max
     * @param self
     * @param min
     * @param max
     * @return
     */
    static int range(final Random self, int min, int max) {
        return new Random().nextInt((max - min) + 1) + min
    }
}
