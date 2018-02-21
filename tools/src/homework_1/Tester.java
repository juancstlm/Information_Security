package homework_1;

import java.math.BigInteger;

public class Tester {
    public static void main(String[] args){
        knapsackTest();
        diffieHellman();
        rsaTest();
    }

    public static void knapsackTest(){
        int b = 8;
        int n = 491; // Modulo
        int m = 41; // Multiplier
        int message = 150; //

        print("Creating a knapsack cryptosystem with 8 bits, modulo = 491, and multiplier = 41");
        Knapsack k = new Knapsack(b,m,n);
        print("Using the knapsack cryptosystem to encrypt the following message: " + message);
        int encrypted = k.encrypt(message);
        print("Encrypted message = " + encrypted);
        print("Using knapsack cryptosystem to decrypt message");
        int plaintex = k.decrypt(encrypted);
        print("Decrypted message plaintext = " + plaintex);
        //print(k.toString());
        print("End of Knapsack Test \n \n");
    }

    public static void rsaTest(){
        print("Creating a RSA to encrypt the following message");
        String message = "Test";
        print("Plaintext Message = " + message);
        RSA rsa = new RSA();
        byte [] encryptedBytes = rsa.encrypt(message.getBytes());
        String encrypted = RSA.bytesToString(encryptedBytes);
        print("Encrypting the message. Encrypted message = " + encrypted);
        byte[]  decryptedBytes = rsa.decrypt(encryptedBytes);
        String decryupted = new String(decryptedBytes);
        print("Decrypting the message to plaintext = " + decryupted);
    }

    public static void diffieHellman(){
        BigInteger g = BigInteger.valueOf(10);
        BigInteger p = BigInteger.valueOf(15487361);
        print("Diffie Hellman Key exchange using g =" + g + " p =" + p);

        BigInteger a = BigInteger.valueOf(12);
        BigInteger b = BigInteger.valueOf(22);
        print("Alice picks her exponent a="+ a.intValue());
        print("Bob picks his exponent b=" + b);

        // Alice computes her thing
        BigInteger gamodp = g.modPow(a,p);
        print("Alice computes g^a mod p using the g and p above and her exponent that bob does not know");
        print("g^a mod p =" + gamodp);

        // Bob computes her thing
        BigInteger gbmodp = g.modPow(b,p);

        print("bob computes g^b mod p using the g and p above and her exponent that alice does not know");
        print("g^b mod p =" + gbmodp);

        print("\n They send each other the computed values");
        // Alice computes gab mod p from the bob
        BigInteger gabmodp = gbmodp.modPow(a,p);
        print("Alice uses g^b mod P she got from bob and computes g^ab mod p = " + gabmodp);

        // Bob computes gab mod p from the alice
        BigInteger gbamodp = gamodp.modPow(b,p);
        print("Bob uses g^a mod P she got from bob and computes g^ba mod p = " + gbamodp);

        print("Success both secrets agree \n \n " );
    }

    /**
     * Prints a message into the command line.
     * @param string
     *  the message to print
     */
    public static void print(String string){
        System.out.println(string);
    }
}
