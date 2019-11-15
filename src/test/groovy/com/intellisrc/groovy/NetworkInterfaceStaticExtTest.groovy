package com.intellisrc.groovy

import spock.lang.Specification


/**
 * @since 17/04/19.
 */
class NetworkInterfaceStaticExtTest extends Specification {
    def "Free port"() {
        setup:
            int port1 = NetworkInterfaceStaticExt.getFreePort()
            int port2 = NetworkInterfaceStaticExt.getFreePort()
        expect:
            assert port1 > 0 && port2 > 0
            assert port1 != port2
    }
    def "Port is available"() {
        setup:
            int port = NetworkInterfaceStaticExt.getFreePort()
        expect:
            assert NetworkInterfaceStaticExt.isPortAvailable(null, port)
    }
    def "Port not available"() {
        setup:
            int port = 1
        expect:
            assert ! NetworkInterfaceStaticExt.isPortAvailable(null, port)
    }
}