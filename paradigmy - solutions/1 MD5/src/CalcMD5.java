import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CalcMD5 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "utf8"))) {
            String fileName;
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                while (true) {
                    fileName = reader.readLine();
                    if (fileName == null) {
                        break;
                    }
                    byte[] bytesFile = Files.readAllBytes(Paths.get(fileName));
                    byte[] digest = messageDigest.digest(bytesFile);
                    digest = messageDigest.digest(bytesFile);
                    StringBuilder md5Hex = new StringBuilder();

                    for (byte i : digest) {
                        md5Hex.append(String.format("%02x", i));
                    }
                    System.out.println(md5Hex);

                    byte[] digest2 = messageDigest.digest(bytesFile);
                    StringBuilder md5Hex2 = new StringBuilder();
                    for (byte i : digest2) {
                        md5Hex2.append(String.format("%02x", i));
                    }
                    System.out.println(md5Hex2 + "2");
                }
            } catch (NoSuchAlgorithmException e) {
                System.out.println("ERROR MD5 упал" + e.getMessage()); // поменять сообщение!
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR input file not found "  + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("ERROR uncorrected encoding "   + e.getMessage());
        } catch (IOException e) {
            System.out.println("ERROR input error " + e.getMessage());
        }
    }
}