import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/* You will need to use this class to build your G52APRClient.
 * It needs to implement the interface IG52APRClient and so it
 * will need to have the methods for httpGet, httpHead and httpPost
 */
public class G52APRClient implements IG52APRClient {

	// Constructor to make G52APRClient object.
	public G52APRClient() {

	}

	@Override
	public String httpGet(String url) {

		HttpGet getRequest = new HttpGet(url);
		getRequest.setProtocolVersion(HttpVersion.HTTP_1_0);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		String returnGet = "";
		try {
			response = httpclient.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			HttpEntity entity1 = response.getEntity();
			returnGet = EntityUtils.toString(entity1);
			EntityUtils.consume(entity1);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnGet;
	}

	@Override
	public String httpHead(String url) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpHead httpHead = new HttpHead(url);
		CloseableHttpResponse response = null;
		Header[] httpHeader = null;
		String responseHead = "";

		try {
			System.out.println("Test");
			response = httpclient.execute(httpHead);
			httpHeader = response.getAllHeaders();
			System.out.println("pass");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("fail");

			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < httpHeader.length; i++) {
			responseHead = responseHead + httpHeader[i] + "\n";
		}
		return responseHead;
	}

	@Override
	public String httpPost(String url, String body) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		String returnPost = null;

		try {
			httpPost.setEntity(new StringEntity(body));
			response = httpclient.execute(httpPost);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			response.getStatusLine();
			HttpEntity entity1 = response.getEntity();
			returnPost = EntityUtils.toString(response.getEntity(), "UTF-8");
			EntityUtils.consume(entity1);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnPost;
	}

	@Override
	public String httpPost(String url, ArrayList<NameValuePair> nvps) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		String returnThis = null;

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);
			response2.getStatusLine();
			returnThis = EntityUtils.toString(response2.getEntity(), "UTF-8");
			HttpEntity entity2 = response2.getEntity();
			EntityUtils.consume(entity2);
			response2.close();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnThis;
	}

}