import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class G52APRServer {

	public static void main(String[] args) {

		int portNumber = Integer.parseInt(args[0]);

		try {

			ServerSocket serverSocket = new ServerSocket(portNumber);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
					true);
			BufferedReader inReader = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			String inString = inReader.readLine();
			RequestHandler reqHandler = new RequestHandler();

			String outString = new String(reqHandler.processRequest(inString
					.getBytes()));
			
			out.println(outString);
			inReader.close();
			out.close();
			serverSocket.close();

		} catch (IOException e) {
			System.out
					.println("Exception caught when trying to listen on port "
							+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}