import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SignFile {
    public static String signFile(String hashFilePath, String privateKeyPath) {
        try {
            String hash = new String(Files.readAllBytes(Paths.get(hashFilePath))).trim();
            String privateKey = new String(Files.readAllBytes(Paths.get(privateKeyPath)));

            BigInteger messageHash = new BigInteger(hash, 16);
            BigInteger d = new BigInteger(privateKey.split("\n")[1].split("= ")[1].trim());
            BigInteger n = new BigInteger(privateKey.split("\n")[2].split("= ")[1].trim());

            BigInteger digitalSignature = messageHash.modPow(d, n);
            saveToFile("signature.txt", digitalSignature.toString());
            return "File Signed and Saved to signature.txt\nSignature:\n" + digitalSignature;
        } catch (Exception ex) {
            return "Error Signing File: " + ex.getMessage();
        }
    }

    private static void saveToFile(String filename, String content) throws Exception {
        Files.write(Paths.get(filename), content.getBytes());
    }
}
