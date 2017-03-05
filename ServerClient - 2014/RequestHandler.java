public class RequestHandler implements IRequestHandler {

	@Override
	public byte[] processRequest(byte[] request) {

		String byteGetter = new String(request);

		String[] parts = byteGetter.split(" ");
		String String1 = parts[0]; // = title
		// String String2 = parts[1];
		// String String3 = parts[2];
		String response = "";

		switch (String1) {

		case "GET":
			response = httpGet();
			break;

		case "HEAD":
			response = httpHead();
			break;

		case "POST":
			response = httpPost();
			break;

		default:
			response = "HTTP/1.0 400 Bad Request\r\n\r\n";
			break;
		}

		request = response.getBytes();

		return request;
	}

	public String httpGet() {

		return "HTTP/1.0 501 GET Not Implemented\r\n\r\n";
	}

	public String httpHead() {

		return "HTTP/1.0 501 HEAD Not Implemented\r\n\r\n";
	}

	public String httpPost() {

		return "HTTP/1.0 501 POST Not Implemented\r\n\r\n";
	}

}
