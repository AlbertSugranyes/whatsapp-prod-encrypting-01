import encrypting.Encrypter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncrypterEndToEndTest {

    @Test
    @DisplayName(value = "All lines written successfully")
    public void encrypterTest() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        // Arrange
        String testFileName = "KPEM03T_PROD_WHATSAPP.txt";
        int[] testFormatKpem03tInicial = {10, 3, 1, 1, 2, 40, 9, 35, 35, 11, 35, 5, 3, 1, 1, 2, 3, 10, 4, 2, 3, 10, 4, 2, 3, 10, 4, 8, 50, 2, 10, 1, 10, 5, 2, 1, 1, 1, 10, 4, 8, 26};
        int[] testKpem03tPi = {5, 6, 7, 8, 9, 10, 11, 13, 17, 18, 21, 22, 25, 26, 28};

        Encrypter encrypter = new Encrypter(testFormatKpem03tInicial, testFileName, testKpem03tPi);

        // Act
        encrypter.encrypt();

        // Assert
        BufferedReader fileValidator = new BufferedReader(new FileReader("Encrypted files/" + testFileName));
        int lineCounter = 0;
        while (fileValidator.readLine() != null) {
            lineCounter++;
        }
        assertEquals(5445049, lineCounter);
    }
}
