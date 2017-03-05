import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class HTTPclientMain {

	public static void main(String[] args) throws IOException {
		G52APRClient client = new G52APRClient();

		String textGet = client.httpGet("http://localhost:4444");

		String textHead = client.httpHead("http://localhost:4444");

		String body = ("Hello World");
		String textPost = client.httpPost("http://localhost:4444", body);

		ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "vip"));
		nvps.add(new BasicNameValuePair("password", "secret"));
		String textPost2 = client.httpPost("http://localhost:4444", nvps);

		System.out.println(textGet + "\n" + textHead + "\n" + textPost + "\n"
				+ textPost2 + "\n");
	}
}
