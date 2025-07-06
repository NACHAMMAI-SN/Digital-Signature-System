import java.io.*;
import java.net.*;

public class DigitalSignatureClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";  
        int serverPort = 5000; 

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            System.out.println("Connected to the Digital Signature Server.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

           
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Choose an operation:");
            System.out.println("1. Verify Signature");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            String choice = userInput.readLine();

            if (choice.equals("1")) {
                
                out.println("verify");

                
                System.out.print("Hashed File Path: ");
                String hashedFilePath = userInput.readLine();
                out.println(hashedFilePath);

                System.out.print("Signature Path: ");
                String signaturePath = userInput.readLine();
                out.println(signaturePath);

                System.out.print("Public Key Path: ");
                String publicKeyPath = userInput.readLine();
                out.println(publicKeyPath);

                
                System.out.println("Waiting for server response...");
                String response = in.readLine();
                System.out.println("Server Response: " + response);
            } else if (choice.equals("2")) {
                System.out.println("Exiting the program.");
            } else {
                System.out.println("Invalid choice. Exiting...");
            }
        } catch (IOException e) {
            System.err.println("Error: Unable to connect to the server or communication issue.");
            e.printStackTrace();
        }
    }
}
