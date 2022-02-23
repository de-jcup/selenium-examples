package de.jcup.example.selenium;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import fi.iki.elonen.NanoHTTPD;

/**
 * Just a simple web server with NanoHTTPD
 * @author de-jcup
 *
 */
public class SimpleWebApp extends NanoHTTPD {

	public static void main(String[] args) throws IOException {
		SimpleWebApp webApp = new SimpleWebApp();
		webApp.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
	}

	public SimpleWebApp() throws IOException {
		super(8080);
	}

	@Override
	public Response serve(IHTTPSession session) {
		Map<String, List<String>> parameters = session.getParameters();
		List<String> userNameList = parameters.get("username");
		String userName = "";
		if (userNameList != null) {
			userName = userNameList.get(0);
		}
		String msg = "<html><head><title>Welcome " + userName + "</title></head><body><h1>Hello server</h1>\n";
		if (userName.isEmpty()) {
			msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n"
					+ "<p><input type='submit'> </p></form>\n";
		} else {
			msg += "<p>Hello, " + userName + "!</p>";
		}
		return newFixedLengthResponse(msg + "</body></html>\n");
	}
}