import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class FileHashadmin {
    public static String hashFile(String filePath) {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = sha256.digest(fileBytes);
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            String hash = hexString.toString();
            saveToFile("hashed_file.txt", hash);
            return "File Hashed and Saved to hashed_file.txt\nHash:\n" + hash;
        } catch (Exception ex) {
            return "Error Hashing File: " + ex.getMessage();
        }
    }

    private static void saveToFile(String filename, String content) throws Exception {
        Files.write(Paths.get(filename), content.getBytes());
    }
}
