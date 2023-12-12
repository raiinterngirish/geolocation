import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class GeoLocationApp {

    public static void main(String[] args) {
        try {
            System.out.println("Your Current IP address: " + getIPAddress());
            System.out.println("Your Current Location (Latitude, Longitude): " + getLocation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getIPAddress() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
                        && inetAddress.isSiteLocalAddress()) {
                    return inetAddress.getHostAddress();
                }
            }
        }
        return "Unable to determine IP address.";
    }

    private static String getLocation() {
        // In a real application, you would use a Geolocation API here.
        // This example just returns a static location for demonstration purposes.
        return "37.7749° N, 122.4194° W"; // San Francisco, CA
    }
}
