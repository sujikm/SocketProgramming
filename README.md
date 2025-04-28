# 💬 Java Socket Programming - Client-Server Chat Application

This project involves building a basic client-server chat application in Java where the client connects to a server over a network to send and receive messages.  
It allows real-time text-based communication between the client and server using TCP/IP sockets.

---

## 🛠️ Technologies Used

- **Java** – Programming language for both client and server
- **Sockets** – Java's `java.net` package for network communication
- **I/O Streams** – `BufferedReader`, `PrintWriter` for reading/writing data
- **Eclipse IDE** – (or any text editor)
- **TCP/IP** – Communication protocol for client-server messaging

---

## 📜 Application Components

### Client Side
- Creates a socket connection to the server (localhost, port 13)
- Uses **PrintWriter** to send messages
- Uses **BufferedReader** to receive server responses
- Exits chat on special command `\q`
- Handles connection errors gracefully (UnknownHostException, IOException)

### Server Side
- Listens for incoming connections using **ServerSocket**
- Accepts one client at a time (single-threaded)
- Receives messages and sends responses
- Continuously handles messages until connection is closed
- Handles network errors gracefully

---

## 🔄 Client-Server Communication
- Communication uses **TCP/IP** sockets.
- Single-threaded design for simplicity: client sends a message, waits for server response in a loop.

---

## ⚙️ How to Run the Application

### Prerequisites
- Java Development Kit (JDK) installed
- Eclipse IDE or any text editor
- Basic command-line usage knowledge

### Setup Steps

1. **Create Project Structure:**
   - Create a new project `NetworkProject`
   - Inside `src` folder, create package `ie.atu.sw`
   - Create the following files:
     - `ChatServer.java`
     - `ChatClient.java`

2. **Compile the Code:**
   ```bash
   cd NetworkProject/src/ie/atu/sw
   javac ChatServer.java ChatClient.java
3. **Run the server: Open the terminal window**
   ```bash
   cd NetworkProject/src
   java ie.atu.sw.ChatServer
  **The server will listen on port 13.**
  -
4.  **Run the client: Open a new terminal window**
   ```bash
    cd NetworkProject/src
    java ie.atu.sw.ChatClient
```
5. 🔄 Interaction Between Client and Server

   - The **client** sends messages to the **server**.
   - The **server** receives and responds back to the client.
   - To **exit the chat**, the client types `\q` and presses Enter 

## 🛡️ Error Handling
   - UnknownHostException: When the client can't resolve the server's address.
   - IOException: When network issues occur (server down, disconnection).
   - If connection is lost during communication, the client gracefully exits and informs the user.
     
## 📡 Running on Different Machines
   - Find the IP address of the server machine.
   - Replace "localhost" in the client code with the server IP address.

## ➕ Additional Features
1. Dynamic Address and Port via Command Line
 - Instead of hardcoding the server address and port:
    - ChatClientCommandLine.java allows the client to accept address and port as command-line arguments.

```bash
  java ie.atu.sw.ChatClientCommandLine localhost 13
```
2 Configuration File Based Connection
  -  ChatClientConfigFile.java reads server IP and port from a config.properties file.
  -  Easier to modify without changing source code.

```bash
   java ie.atu.sw.ChatClientConfigFile
 ```
# ❔ Questions & Answers
## 1. How does the client know what address to find the server at?
  - Initially hardcoded as:
```bash
   hostname = "localhost";
   int port = 13;
 ```
 -  Improved with:
    - Command-line arguments
    - Configuration files
## 2. What happens if the client can’t reach the server when starting?
   - It throws:
      - UnknownHostException
      - IOException
   - User is informed with an error message.
## 3. What happens if the connection is lost during the chat session?
   - On reading from or writing to a dead socket, IOException is thrown.
   - Client catches the exception and exits the chat gracefully.

Example
```bash
catch (IOException ex) {
    System.err.println("Error during chat session: " + ex.getMessage());
    System.out.println("Connection lost. Exiting chat.");
}
```
## 📚 References
  - Java Socket Programming Official Documentation
  - Oracle Java IO Streams Documentation
  - ATU Virtual Learning Platform Resources
