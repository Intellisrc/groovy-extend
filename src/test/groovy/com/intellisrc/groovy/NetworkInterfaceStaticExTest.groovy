package com.intellisrc.groovy

import spock.lang.Specification


/**
 * @since 17/04/19.
 */
class NetworkInterfaceStaticExTest extends Specification {
    def "Free port"() {
        setup:
            int port1 = NetworkInterfaceStaticEx.getFreePort()
            int port2 = NetworkInterfaceStaticEx.getFreePort()
        expect:
            assert port1 > 0 && port2 > 0
            assert port1 != port2
    }
}