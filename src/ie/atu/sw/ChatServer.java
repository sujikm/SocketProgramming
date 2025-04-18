package ie.atu.sw;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class ChatServer {
	 // Port number where the server listen for connections
	public final static int PORT = 13;

    public static void main(String[] args) {
    	System.out.println("Listening for connection on port " + PORT);
        try (ServerSocket server = new ServerSocket(PORT); 
        		BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in))){
        	// Run indefinitely to accept multiple client connections one at a time
            while (true) {
            	 System.out.println("Waiting for the next client...");
                try (Socket connection = server.accept()) {
                	 // Display the client details
                    System.out.println("Client connected from host " + connection.getInetAddress()
                    + ", port " + connection.getPort());
                    
                 // Set up input and output streams to communicate with the client
                    
                    try (
                            BufferedReader clientInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            PrintWriter serverOutput = new PrintWriter(connection.getOutputStream(), true);
                            
                        ) {
                            String clientMessage, serverMessage;

                            // Server sends a welcome message
                            serverOutput.println("Welcome to the chat server! Type '\\q' to exit.");

                         //the server keeps sending and receiving messages until the session ends
                            while (true) {
                                // Read message from the client
                                clientMessage = clientInput.readLine();
                                System.out.println(clientMessage);
                                if (clientMessage == null || clientMessage.equalsIgnoreCase("\\q")) {
                                    System.out.println("Client disconnected.");
                                    break;
                                }
                                System.out.println("Client: " + clientMessage);

                                // // Send the server's message to the client
                                System.out.print("Server: ");
                                serverMessage = serverInput.readLine();
                                serverOutput.println(serverMessage);

                                if (serverMessage.equalsIgnoreCase("\\q")) {
                                    System.out.println("Chat session ended.");
                                    break;
                                }
                            }
                         // Handle any I/O errors during communication with the client
                        } catch (IOException ex) {
                            System.err.println("Error during chat session: " + ex.getMessage());
                        }
                    // Handle any connection-related errors
                    } catch (IOException ex) {
                        System.err.println("Connection error: " + ex.getMessage());
                    }
               
                }
            } catch (IOException ex) {
                System.err.println("Server error: " + ex.getMessage());
            }
        }
    }
