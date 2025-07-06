import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VerifySignature {
    public static String verifySignature(String signatureFilePath, String publicKeyPath, String hashedFilePath) {
        try {
            // Read the signature, public key, and hashed file contents
            String signature = new String(Files.readAllBytes(Paths.get(signatureFilePath))).trim();
            String publicKey = new String(Files.readAllBytes(Paths.get(publicKeyPath)));
            String fileHash = new String(Files.readAllBytes(Paths.get(hashedFilePath))).trim();

            System.out.println("Signature: " + signature);
            System.out.println("Public Key: " + publicKey);
            System.out.println("File Hash: " + fileHash);

            BigInteger digitalSignature = new BigInteger(signature);
            BigInteger eKey = new BigInteger(publicKey.split("\n")[1].split("= ")[1].trim());
            BigInteger n = new BigInteger(publicKey.split("\n")[2].split("= ")[1].trim());
            BigInteger verifiedHash = digitalSignature.modPow(eKey, n);
            String verifiedHashHex = verifiedHash.toString(16);

            System.out.println("Decrypted hash (from signature): " + verifiedHashHex);

            // Verify if the decrypted hash matches the file hash
            if (verifiedHash.toString(16).equals(fileHash)) {
                return "Decrypted Hash (from signature): " + verifiedHashHex + "\nSignature Verified: File integrity is verified.";
            } else {
                return "Signature Verification Failed: The file has been tampered.";
            }

        } catch (Exception ex) {
            return "Error Verifying Signature: " + ex.getMessage();
        }
    }
}
