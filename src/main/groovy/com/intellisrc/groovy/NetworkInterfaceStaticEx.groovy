package com.intellisrc.groovy

@groovy.transform.CompileStatic
/**
 * Static extension methods for NetworkInterface
 * NOTE: Instance and Static methods should be defined separately:
 * see: http://docs.groovy-lang.org/next/html/documentation/core-metaprogramming.html#_extending_existing_classes
 * @author Alberto Lepe <lepe@sharelock.jp>
 */
class NetworkInterfaceStaticEx {
    /**
     * Retrieves the first IP address that is not 127.X.X.X
     * @return
     */
    static Inet4Address getLocalIP(final NetworkInterface self) {
        getInet4Addresses(self).find {
            !it.toString().startsWith("127.")
        }
    }

    /**
     * Test if local device contains such IP address
     * @param address
     * @return if has it
     */
    static boolean isLocalAddress(final NetworkInterface self, final Inet4Address address) {
        getInet4Addresses(self).find {
            it == address
        }
    }

    /**
     * Retrieves the first IP address that starts with...
     * NOTE: For less controled environment, use getIPinNetwork() with fixed network and segments
     *
     * @param segment
     * @return
     *
     */
    static Inet4Address getIPStartWith(final NetworkInterface self, final String segment) {
        getInet4Addresses(self).find {
            it.toString().startsWith(segment)
        }
    }

    /**
     * Retrieves the first IP address that is in Network
     * @param network : X.X.X.0
     * @param subnetbit : 24
     * @return
     */
    static Inet4Address getIPinNetwork(final NetworkInterface self, final Inet4Address network, int subnetbit) {
        getInet4Addresses(self).find {
            Inet4AddressExt.isNetSegment(it, network, subnetbit)
        }
    }

    /**
     * Retrieves the first IP address that is in Network
     * @param network : X.X.X.0
     * @param netmask : 255.255.255.0
     * @return
     */
    static Inet4Address getIPinNetwork(final NetworkInterface self, final Inet4Address network, Inet4Address netmask) {
        getInet4Addresses(self).find {
            Inet4AddressExt.isNetSegment(it, network, netmask)
        }
    }

    /**
     * Retrieves an available port.
     * NOTE: Be aware that it is not protected against race conditions
     * @return
     */
    static int getFreePort(final NetworkInterface self) {
        int port = 0
        try {
            ServerSocket socket = new ServerSocket(0)
            port = socket.localPort
            socket.close()
        } catch (Exception e) {
            System.err.println("Unable to get free port")
        }
        return port
    }

    /**
     * Retrieves all IP addresses registered in localhost
     * from: http://stackoverflow.com/questions/9481865/
     * @return
     */
    static ArrayList<Inet4Address> getInet4Addresses(final NetworkInterface self) {
        def ips = new ArrayList<Inet4Address>()
        try {
            def interfaces = self.getNetworkInterfaces()

            while(interfaces.hasMoreElements()){
                java.net.NetworkInterface i = interfaces.nextElement()
                if(i != null){
                    Enumeration<InetAddress> addresses = i.getInetAddresses()
                    while(addresses.hasMoreElements()){
                        InetAddress address = addresses.nextElement()
                        if(address instanceof Inet4Address) {
                            ips.add((Inet4Address) address)
                        }
                    }
                }
            }
        }
        catch(SocketException e){
            ips.add((Inet4Address) InetAddress.getByName("127.0.0.1"))
        }
        return ips
    }

}
