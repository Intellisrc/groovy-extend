package jp.sharelock.groovy

import groovy.transform.CompileStatic

/**
 * @since 17/11/29.
 */
@CompileStatic
class MapExt {
    /**
     * Encodes a Map as query string
     * e.g.: key1=val1&key2=val2
     * @param self
     * @return
     */
    static String toQueryString(Map self) {
        return self.collect {
            key, val ->
                return URLEncoder.encode(key.toString(), "UTF-8") + "=" + URLEncoder.encode(val.toString(), "UTF-8")
        }.join('&')
    }
}
