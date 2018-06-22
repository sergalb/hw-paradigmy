import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Sum {
    private static void calcHash(MessageDigest messageDigest, String fileName) {
        byte[] digest = messageDigest.digest();
        StringBuilder md5Hex = new StringBuilder();
        for (byte i : digest) {
            md5Hex.append(String.format("%02x", i));
        }
        System.out.println(md5Hex + " *" + fileName);
    }
    public static void main(String[] args) throws IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            if (args.length == 0) {
                byte[] buffer = new byte[1 << 16];
                int countBytes;
                while (true) {
                    countBytes = System.in.read(buffer);
                    if (countBytes == -1) {
                        break;
                    }
                    messageDigest.update(buffer, 0, countBytes);
                }
                calcHash(messageDigest, "-");
                return;
            }
            try  {
                for (String fileName : args) {
                    messageDigest.update(Files.readAllBytes(Paths.get(fileName)));
                    calcHash(messageDigest, fileName);
                }

            } catch (FileNotFoundException e) {
                System.out.println("ERROR input file not found " + e.getMessage());
            } catch (UnsupportedEncodingException e) {
                System.out.println("ERROR uncorrected encoding " + e.getMessage());
            } catch (IOException e) {
                System.out.println("ERROR input error " + e.getMessage());
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("ERROR algorithm crashed" + e.getMessage());
        }
    }
}