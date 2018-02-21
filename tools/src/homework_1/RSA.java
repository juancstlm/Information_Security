package homework_1;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
    private BigInteger p1;
    private BigInteger p2;
    private BigInteger e;
    private BigInteger phi;
    private BigInteger N;
    private BigInteger d;
    private int        bitlength = 128;
    private Random r;

    public RSA(){
        r = new Random(3);
        p1 = BigInteger.probablePrime(bitlength, r);
        p2 = BigInteger.probablePrime(bitlength, r);
        N = p1.multiply(p2);
        phi = p1.subtract(BigInteger.ONE).multiply(p2.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
        {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }

    public RSA(int p1,int p2,int e){

    }

    // Encrypt message
    public byte[] encrypt(byte[] message)
    {
        byte[] returned = (new BigInteger(message)).modPow(e, N).toByteArray();
        return returned;
    }


    // Decrypt message
    public byte[] decrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }

    public static String bytesToString(byte[] encrypted)
    {
        String test = "";
        for (byte b : encrypted)
        {
            test += Byte.toString(b);
        }
        return test;
    }
}
