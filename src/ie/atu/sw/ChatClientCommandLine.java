package ie.atu.sw;
import java.io.*;
import java.net.*;
public class ChatClientCommandLine {
	 public static void main(String[] args) {
		 if (args.length != 2) {
	            System.out.println("Usage: java ChatClient <hostname> <port>");
	            return;
	        }

	        String hostname = args[0]; // First argument is server address
	        int port = Integer.parseInt(args[1]); // Second argument is server port
	        System.out.println("Client is attempting to connect to the server...");
	            try (Socket socket = new Socket(hostname, port)) {
	                System.out.println("Connected to server at " + hostname + ":" + port);

		            try (
			                BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Read server messages
			                PrintWriter clientOutput = new PrintWriter(socket.getOutputStream(), true); // Send messages to the server
			                BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in)) // Read user input
			            ) {
			                String serverMessage, clientMessage;

			                // Display the server's welcome message
			                serverMessage = serverInput.readLine();
			                System.out.println("Server: " + serverMessage);

			                // Chat loop
			                while (true) {
			                    // Client sends a message
			                    System.out.print("Client: ");
			                    clientMessage = clientInput.readLine(); // Read from the console
			                    clientOutput.println(clientMessage); // Send to the server

			                    // Exit the chat if the client types "\q"
			                    if (clientMessage.equalsIgnoreCase("\\q")) {
			                        System.out.println("You ended the chat.");
			                        break;
			                    }

			                    // Client receives a message from the server
			                    serverMessage = serverInput.readLine(); // Read from the server
			                    if (serverMessage == null || serverMessage.equalsIgnoreCase("\\q")) {
			                        System.out.println("Server ended the chat.");
			                        break;
			                    }
			                    System.out.println("Server: " + serverMessage);
			                }
			            } catch (IOException ex) {
			                System.err.println("Error during chat session: " + ex.getMessage());
			            }
			        
	            } catch (IOException e) {
	                System.err.println("Error connecting to server: " + e.getMessage());
	            }
	        
	 
	 }
}
