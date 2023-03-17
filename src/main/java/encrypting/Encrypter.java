package encrypting;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encrypter {

    private final int[] widths;
    private final String fileName;
    private final int[] piFieldIndexes;
    BufferedWriter writer;


    public Encrypter(int[] widths, String fileName, int[] piFieldIndexes) {
        this.widths = widths;
        this.fileName = fileName;
        this.piFieldIndexes = piFieldIndexes;
    }

    public void encrypt() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("files/" + fileName))) {
            writer = new BufferedWriter(new FileWriter("Encrypted files/" + fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = parseLine(line);
                encryptPersonalData(fields);
                write(fields);
            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String[] parseLine(String line) {
        String[] parsedLine = new String[this.widths.length];
        int currentPosition = 0;

        for (int i = 0; i < this.widths.length; i++) {
            try {
                parsedLine[i] = line.substring(currentPosition, currentPosition + this.widths[i]);
            } catch (StringIndexOutOfBoundsException e) {
                parsedLine[i] = spaceGenerator(this.widths[i]);
            } finally {
                currentPosition += this.widths[i];
            }
        }

        return parsedLine;
    }

    private void encryptPersonalData(String[] fields) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String key = "zurichCRPTas16ws";
        Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CFB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        for (int i = 0; i < fields.length; i++) {
            if (isPersonalData(i)) {
                byte[] bytes = fields[i].getBytes();
                byte[] crpt = cipher.doFinal(bytes);
                fields[i] = Base64.getEncoder().encodeToString(crpt);
            }
        }
    }

    private boolean isPersonalData(int position) {

        for (int piFieldIndex : this.piFieldIndexes) {
            if (piFieldIndex == position) {
                return true;
            }
        }
        return false;
    }

    private void write(String[] fields) throws IOException {

        String data = String.join("", fields);
        writer.write(data);
        writer.newLine();
    }

    private String spaceGenerator(int width) {
        return " ".repeat(width);
    }
}

