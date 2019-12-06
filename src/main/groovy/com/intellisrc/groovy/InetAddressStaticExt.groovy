package com.intellisrc.groovy

import groovy.transform.CompileStatic

/**
 * Created by lepe on 17/02/22.
 */
@CompileStatic
class InetAddressStaticExt {
    /**
     * Generates a random IP4
     * @return
     */
    static Inet4Address randomIP4(final InetAddress self) {
        Random rand = new Random()
        return StringExt.toInet4Address(rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256))
    }
}
