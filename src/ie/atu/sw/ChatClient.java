package ie.atu.sw;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
    	// Default server hostname and port
        String hostname = "localhost";
        int port = 13;
     // Create a socket connection to the server
        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("Connected to the server!");

            // Create input/output streams for communication
            try (
                BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter clientOutput = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in))
            ) {
                String serverMessage, clientMessage;

                // Display the server's welcome message
                serverMessage = serverInput.readLine();
                System.out.println("Server: " + serverMessage);

             // Chat loop to continue the conversation
                while (true) {
                    // Client sends a message
                    System.out.print("Client: ");
                    clientMessage = clientInput.readLine();
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
             // If an error occurs during communication
            } catch (IOException ex) {
                System.err.println("Error during chat session: " + ex.getMessage());
            }
        } catch (UnknownHostException ex) {
        	// If the server is not found 
            System.err.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
        	 // If an I/O error occurs
            System.err.println("I/O error: " + ex.getMessage());
        }
    }
}
