import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DigitalSignatureServer {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Digital Signature Server is running on port " + port + "...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connected.");

                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                   
                    String requestType = in.readLine();
                    if (requestType != null && requestType.equals("verify")) {
                       
                        String hashedFilePath = in.readLine();
                        String signaturePath = in.readLine();
                        String publicKeyPath = in.readLine();

                        System.out.println("Received file paths for verification:");
                        System.out.println("Hashed File Path: " + hashedFilePath);
                        System.out.println("Signature Path: " + signaturePath);
                        System.out.println("Public Key Path: " + publicKeyPath);

                        
                        if (!Files.exists(Paths.get(hashedFilePath)) || 
                            !Files.exists(Paths.get(signaturePath)) || 
                            !Files.exists(Paths.get(publicKeyPath))) {
                            out.println("Error: One or more files do not exist.");
                            out.println("END");
                            continue;
                        }

                        
                        String response = VerifySignature.verifySignature(signaturePath, publicKeyPath, hashedFilePath);
                        out.println(response);
                        out.println("END");
                    } else {
                        out.println("Error: Unsupported request type.");
                        out.println("END");
                    }
                } catch (IOException e) {
                    System.err.println("Error handling client request: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
