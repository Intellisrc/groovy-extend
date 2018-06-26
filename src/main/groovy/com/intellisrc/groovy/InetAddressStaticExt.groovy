package com.intellisrc.groovy

@groovy.transform.CompileStatic
/**
 * Created by lepe on 17/02/22.
 */
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
