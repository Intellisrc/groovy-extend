package com.intellisrc.groovy

import groovy.transform.CompileStatic

/**
 * Extends collections
 * @since 2020/02/18.
 */
@CompileStatic
class CollectionExt {
    /**
     * Get one random item from list
     * @param self
     * @return
     */
    static <T> T random(final Collection<? extends T> self) {
        Random random = new Random()
        return self[random.nextInt(self.size())]
    }
    /**
     * Get one or more random elements from a list
     * @param self
     * @param elements
     * @return
     */
    static <T> List<T> random(final Collection<? super T> self, int elements) {
        if(elements >= 0 && self.size() > 0) {
            List<T> copy = []
            copy.addAll(self)
            Collections.shuffle(copy)
            return copy.subList(0, ([elements, copy.size()].min() as int))
        } else {
            return []
        }
    }
}
