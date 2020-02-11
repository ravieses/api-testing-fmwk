package dataproviders.request;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

public class RequestProperties {
	private static RequestProperties instance;
	private Map<String, String> headers = new HashedMap<String, String>();
	private String URL;

	private RequestProperties() {
		headers.put("Content-Type", "application/json");
		headers.put("x-apikey", "5da6fb5d3cbe87164d4bb35d");
		setURL("https://flobizhiring-57e6.restdb.io/rest/issues");
	}

	public static RequestProperties getInstance() {
		if (instance == null)
			instance = new RequestProperties();
		return instance;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

}
