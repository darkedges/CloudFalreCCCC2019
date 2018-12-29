import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import inet.ipaddr.AddressStringException;
import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;
import inet.ipaddr.IncompatibleAddressException;

public class App {

	String testTime[] = { "2400:cb00::/32", "405:b500::/32", "2606:4700::/32", "2803:f800::/32", "2c0f:f248::/32",
			"2a06:98c0::/29" };

	public static void main(String[] args) throws Exception {
		App app = new App();
		app.execute();
	}

	private void execute() throws Exception {
		Gson g = new Gson();
		JsonReader jsonReader = new JsonReader(new FileReader("rpki.json"));
		RPKI person = g.fromJson(jsonReader, RPKI.class);
		List<Roa> roas = person.getRoas();
		for (Iterator<Roa> iterator = roas.iterator(); iterator.hasNext();) {
			Roa roa = iterator.next();
			for (int i = 0; i < testTime.length; i++) {
				String r = testTime[i];
				if (overlap(roa.getPrefix(), r)) {
					System.out.println(roa.getPrefix());
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
	private static boolean overlap(final String net1, final String net2)
			throws AddressStringException, IncompatibleAddressException {
		IPAddressString addrString = new IPAddressString(net1);
		IPAddressString subnetString = new IPAddressString(net2);

		IPAddress addr = addrString.toAddress();
		IPAddress subnet = subnetString.toAddress();
		return subnet.contains(addr); // true in this case
	}
}
