package jp.sharelock.groovy

import org.apache.commons.net.util.SubnetUtils
import org.apache.commons.net.util.SubnetUtils.SubnetInfo

@groovy.transform.CompileStatic
/**
 * @since 2/19/17.
 */
class Inet4AddressExt {

    /**
     * Return the first 3 parts of an IPv4 address
     * NOTE: For less controled environment, use isNetSegment with fixed network and segments
     *
     * @param address
     * @return
     *
     */
    static String getNetSegment24(final Inet4Address self) {
        return self.getHostAddress().replaceFirst('\\d+$', "")
    }

    /**
     * Checks if an address seems to be from the same network of another one
     * NOTE: If the network is not /24, it may fail.
     *
     * @param address
     * @param compare_address
     * @return
     */
    static boolean isFromSameSegment24(final Inet4Address self, final Inet4Address compare_address) {
        return getNetSegment24(self) == getNetSegment24(compare_address)
    }
    /**
     * Checks if 2 addresses share the same network
     * 10.0.0.23 in 10.0.0.0/24 -> (10.0.0.23, 10.0.0.0, 255.255.255.0)
     *
     * @param address : X.X.X.X
     * @param network : X.X.X.0
     * @param netmask : 255.255.255.0
     * @return
     */
    static boolean isNetSegment(final Inet4Address self, final Inet4Address network, final Inet4Address netmask) {
        SubnetInfo subnet = (new SubnetUtils(network.getHostAddress(), netmask.getHostAddress())).getInfo()
        return subnet.isInRange(self.getHostAddress())
    }
    /**
     * Check if an address is inside a network/
     * 10.0.0.23 in 10.0.0.0/24 -> (10.0.0.23, 10.0.0.0, 24)
     *
     * @param address : X.X.X.X
     * @param network : X.X.X.0
     * @param subnetbit : 24
     * @return
     */
    static boolean isNetSegment(final Inet4Address self, final Inet4Address network, final int subnetbit) {
        String cidr = network.getHostAddress()+"/"+subnetbit
        SubnetInfo subnet = (new SubnetUtils(cidr)).getInfo()
        return subnet.isInRange(self.getHostAddress())
    }

    /**
     * Generates a random IP which is from the same /24 network than the specified
     * @param ip
     * @return
     */
    static Inet4Address randomIPSameSegment24(final Inet4Address self) {
        String simIP = self.getHostAddress()
        String[] parts = simIP.split(/\./)
        //Prevent returning the same IP address
        Random rand = new Random()
        while(simIP == self.getHostAddress()) {
            simIP = parts[0] + "." + parts[1] + "." + parts[2] + "." + rand.nextInt(256)
        }
        return StringExt.toInet4Address(simIP)
    }

}
