package encrypting;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class Main {
    public static final Logger logger = Logger.getLogger("mainLogger");
    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        final int[] formatKcim06tInicial = {805};// {14,6,5,6,1,1,10,10,10,5,3,6,6,4,4,10,4,10,1,5,14,1,3,1,1,3,10,1,1,4,1,1,1,1,1,8,10,10,10,10,10,10,1,10,1,1,1,4,1,1,9,1,1,10,3,1,1,9,40,30,30,2,40,9,35,35,35,11,5,3,1,10,4,4,2,10,1,10,10,10,10,10,10,10,3,1,10,2,1,9,17,6,1,1,14,4,2,1,1,10,34,8,10,4,8,26};
        final int[] formatKpem03tInicial = {383};// {10,3,1,1,2,40,9,35,35,11,35,5,3,1,1,2,3,10,4,2,3,10,4,2,3,10,4,8,50,2,10,1,10,5,2,1,1,1,10,4,8,26};
        final int[] formatKpem08tInicial = {610};// {10,3,1,1,9,2,30,30,30,30,40,40,3,10,35,11,3,1,1,10,4,5,3,40,3,1,10,10,10,2,2,2,9,10,10,10,1,10,2,9,10,10,10,1,10,1,1,10,1,1,1,10,10,10,1,1,2,10,1,10,1,8,1,1,4,12,1,10,10,4,8,26};

        int[] kpem03tPi =  {0}; // {5,6,7,8,9,10,11,13,17,18,21,22,25,26,28}
        int[] kpem08tPi = {0}; // {5,6,7,8,9,10,11,13,14,19,23,26,27,28,32,33,34,35,37,39,40,41,42,44,47,51,52,53,57,59}
        int[] kcim06tPi = {0}; // {14,15,16,17,23,24,26,29,35,36,37,38,39,40,41,43,53,54,57,58,59,60,62,63,64,65,66,68,71,72,73,74,75,79,80,81,82,83,86,99,100,101}

        Encrypter kpem03tEncripter = new Encrypter(formatKpem03tInicial,"KPEM03T_PROD_WHATSAPP.TXT",kpem03tPi);
        Encrypter kpem08tEncripter = new Encrypter(formatKpem08tInicial,"KPEM08T_PROD_WHATSAPP.TXT",kpem08tPi);
        Encrypter kcim06tEncripter = new Encrypter(formatKcim06tInicial,"KCIM06T_PROD_WHATSAPP.TXT",kcim06tPi);

        logger.info("Encrypting KPEM03T...");
        kpem03tEncripter.encrypt();
        logger.info("Successfully encrypted KPEM03T");
        logger.info("Encrypting KPEM08T...");
        kpem08tEncripter.encrypt();
        logger.info("Successfully encrypted KPEM08T");
        logger.info("Encrypting KCIM06T...");
        kcim06tEncripter.encrypt();
        logger.info("Successfully encrypted KCIM06T");
    }
}