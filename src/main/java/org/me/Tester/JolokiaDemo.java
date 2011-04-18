package org.me.Tester;

import java.util.Map;

import org.jolokia.client.J4pClient;
import org.jolokia.client.request.J4pReadRequest;
import org.jolokia.client.request.J4pReadResponse;

public class JolokiaDemo {

	public static final String JOLOKIA_URL = "http://localhost:8080/jolokia-war-0.80";

	public static void main(String[] args) throws Exception {
		J4pClient j4pClient = new J4pClient(JOLOKIA_URL);
		J4pReadRequest req = new J4pReadRequest("java.lang:type=Memory",
				"HeapMemoryUsage");

		while (true) {
			J4pReadResponse resp = j4pClient.execute(req);
			Map<String, String> vals = resp.getValue();
			int used = Integer.valueOf(vals.get("used")).intValue();
			int max = Integer.valueOf(vals.get("max")).intValue();
			int usage = (int) (used * 100 / max);
			System.out.println("Memory usage: used: " + used + " / max: " + max
					+ " = " + usage + "%");
			
		}
	}
}
