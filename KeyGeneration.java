import java.math.BigInteger;
import java.security.SecureRandom;
import java.io.FileWriter;
import java.io.IOException;

public class KeyGeneration {
    public static String generateKeys() {
        try {
            SecureRandom randomGenerator = new SecureRandom();

            BigInteger p = BigInteger.probablePrime(512, randomGenerator);
            BigInteger q;
            do {
                q = BigInteger.probablePrime(512, randomGenerator);
            } while (p.equals(q));
        
            BigInteger e = BigInteger.valueOf(65537);

            BigInteger n = p.multiply(q);
           
            BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

            while (!phi.gcd(e).equals(BigInteger.ONE)) {
                p = BigInteger.probablePrime(512, randomGenerator);
                q = BigInteger.probablePrime(512, randomGenerator);
                n = p.multiply(q);
                phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
            }

            BigInteger d = e.modInverse(phi);

            String publicKeyContent = "Public Key:\ne = " + e + "\nn = " + n;
            String privateKeyContent = "Private Key:\nd = " + d + "\nn = " + n;

            saveKeyToFile("public_key.txt", publicKeyContent);
            saveKeyToFile("private_key.txt", privateKeyContent);

            return "Keys Generated and Saved to Files:\npublic_key.txt\nprivate_key.txt";

        } catch (Exception ex) {
            return "Error Generating Keys: " + ex.getMessage();
        }
    }

    private static void saveKeyToFile(String filename, String content) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(content);
        }
    }
}
