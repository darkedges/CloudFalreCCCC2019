
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import inet.ipaddr.AddressStringException;
import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;
import inet.ipaddr.IncompatibleAddressException;

/**
 * Hello world!
 *
 */
public class App {
	String testTime[] = { "2400:cb00::/32", "405:b500::/32", "2606:4700::/32", "2803:f800::/32", "2c0f:f248::/32",
			"2a06:98c0::/29", "103.21.244.0/22", "103.22.200.0/22", "103.31.4.0/22", "104.16.0.0/12",
			"108.162.192.0/18", "131.0.72.0/22", "141.101.64.0/18", "162.158.0.0/15", "172.64.0.0/13",
			"173.245.48.0/20", "188.114.96.0/20", "190.93.240.0/20", "197.234.240.0/22", "198.41.128.0/17" };

	enum ipVersion {
		IPv4, IPv6, BOTH
	}

	public static void main(String[] args) throws Exception {
		App app = new App();
		app.execute();
	}

	private void execute() throws Exception {
		Gson g = new Gson();
		JsonReader jsonReader = new JsonReader(new FileReader("ripe-rpki.json"));
		RPKI person = g.fromJson(jsonReader, RPKI.class);
		List<Roa> roas = person.getRoas();
		System.out.println(
				padLeft("Prefix", 50) + padLeft("Asn", 20) + padLeft("Ta", 50) + padLeft("" + "MaxLength", 10));
		for (Iterator<Roa> iterator = roas.iterator(); iterator.hasNext();) {
			Roa roa = iterator.next();
			for (int i = 0; i < testTime.length; i++) {
				String r = testTime[i];
				if (overlap(ipVersion.IPv6, roa.getPrefix(), r)) {
					if (roa.getMaxLength() == 128)
						System.out.println(padLeft(roa.getPrefix(), 50) + padLeft(roa.getAsn(), 20)
								+ padLeft(roa.getTa(), 50) + padLeft("" + roa.getMaxLength(), 10));
					break;
				}
			}
		}
	}

	/**
	 * https://stackoverflow.com/a/40079805
	 * 
	 * @param net1
	 * @param net2
	 * @return
	 * @throws IncompatibleAddressException
	 * @throws AddressStringException
	 */
	private static boolean overlap(final ipVersion type, final String net1, final String net2)
			throws AddressStringException, IncompatibleAddressException {
		IPAddressString addrString = new IPAddressString(net1);
		IPAddressString subnetString = new IPAddressString(net2);

		IPAddress addr = addrString.toAddress();
		IPAddress subnet = subnetString.toAddress();
		switch (type) {
		case IPv4:
			if (subnet.isIPv4()) {
				return subnet.contains(addr);
			}
			break;
		case IPv6:
			if (subnet.isIPv6()) {
				return subnet.contains(addr);
			}
			break;
		case BOTH:
			return subnet.contains(addr);
		}
		return false;
	}

	/**
	 * https://stackoverflow.com/a/391978
	 * 
	 * @param s
	 * @param n
	 * @return
	 */
	public static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);
	}
}
