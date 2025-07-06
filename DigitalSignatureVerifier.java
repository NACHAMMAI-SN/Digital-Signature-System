import java.io.*;
import java.security.*;
import java.nio.file.*;
import java.util.Base64;
import java.security.spec.X509EncodedKeySpec;

public class VerifySignature {

    
    public static String verifySignature(String signaturePath, String publicKeyPath, String hashedFilePath) {
        try {
            
            byte[] signatureBytes = Files.readAllBytes(Paths.get(signaturePath)); 
            
           
            byte[] decodedSignatureBytes = Base64.getDecoder().decode(signatureBytes); 

           
            byte[] publicKeyBytes = Files.readAllBytes(Paths.get(publicKeyPath)); 
            
           
            byte[] hashedFileBytes = Files.readAllBytes(Paths.get(hashedFilePath));  

            
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

           
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);
            signature.update(hashedFileBytes);  

          
            boolean isVerified = signature.verify(decodedSignatureBytes); 

            if (isVerified) {
                return "Signature Verified Successfully!";
            } else {
                return "Signature Verification Failed!";
            }

        } catch (IOException e) {
            return "Error reading files: " + e.getMessage();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | InvalidKeySpecException e) {
            return "Error Verifying Signature: " + e.getMessage();
        }
    }
}
