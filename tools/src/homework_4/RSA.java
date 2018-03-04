package homework_4;

import sun.misc.BASE64Encoder;
import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPrivateKeyImpl;
import sun.security.x509.X509CertImpl;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;


public class RSA {

    public static final String PUBLIC_KEY_FILE = "C:\\Users\\Juan Castillo\\Documents\\GitHub\\cs-166\\tools\\src\\homework_4\\cs166teacher.cer";
    public static final String FILE = "C:\\Users\\Juan Castillo\\Documents\\GitHub\\cs-166\\tools\\src\\homework_4\\hw4.rsa";

    public static final String MY_CERT = "C:\\Users\\Juan Castillo\\juancastillo.cer";
    public static final String MY_KEYSTORE = "C:\\Users\\Juan Castillo\\mystore.jks";


    public static void main(String[] args) {
        decrypt();
        encrypt("Juan Castillo", MY_CERT, MY_KEYSTORE);
    }

    public static void encrypt(String s, String cert, String key){
        try{
            Cipher c = Cipher.getInstance("RSA/ECB/NoPadding");
           PrivateKey pk = getPrivateKeyFromKeyStore(key,"cookies-11", "cs166", "cookies-11");
            c.init(Cipher.ENCRYPT_MODE,pk);
            byte[] result = c.doFinal(s.getBytes());
            FileOutputStream fos = new FileOutputStream("ciphertext.rsa");
            fos.write(result);
            fos.close();
            print(new String(Base64.getEncoder().encode(result)));
        }
        catch (Exception ignored){
            print(ignored.toString());
        }

    }

    private static PrivateKey getPrivateKeyFromKeyStore(String keyStoreFilePath, String keyStorePassword, String privateKeyCertAlias, String privateKeyPassword) {
        PrivateKey privateKey = null;
        try {
            KeyStore keystore = KeyStore.getInstance("JKS");
            keystore.load(new FileInputStream(keyStoreFilePath), keyStorePassword.toCharArray());
            Key key=keystore.getKey(privateKeyCertAlias,privateKeyPassword.toCharArray());
            if(key instanceof PrivateKey) {
                Certificate cert=keystore.getCertificate(privateKeyCertAlias);
                PublicKey publicKey=cert.getPublicKey();
                KeyPair keyPair = new KeyPair(publicKey,(PrivateKey)key);
                privateKey = keyPair.getPrivate();
            }
        } catch (Exception e) {
            print(e.toString());
        }
        return privateKey;
    }

    public static void decrypt(){

        try{
            Cipher c = Cipher.getInstance("RSA/ECB/NoPadding");
            Certificate certificate = new X509CertImpl(Files.readAllBytes(Paths.get(PUBLIC_KEY_FILE)));
            c.init(Cipher.DECRYPT_MODE, certificate);
            byte[] data  =  Base64.getDecoder().decode(Files.readAllBytes(Paths.get(FILE)));
            byte[] result = c.doFinal(data);
            String s = new String(result);
            print(s);
        }catch(Exception e){e.printStackTrace();}
    }

    static void print(String s){
        System.out.println(s);
    }
}
