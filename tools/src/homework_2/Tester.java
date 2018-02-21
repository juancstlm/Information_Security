package homework_2;

public class Tester {
    public static void main(String[] args){
        rC4Tester();
    }

    public static void rC4Tester(){
        String key = "1A2B3C4D5E6F77";
        byte[] keyByteArray = hexStringToByteArray(key);

        RC4 rc100 = new RC4(keyByteArray);
        System.out.println("Initialization completed Printing out S");
        rc100.printSPermutations();
        System.out.println("Printing out indexes after initialization");
        rc100.printIndexes();


        System.out.println("Generating first 100 bytes");
        rc100.getKeystream(100);
        System.out.println("Printing S after 100 bytes");
        rc100.printSPermutations();
        System.out.println("Printing indexes after 100 bytes");
        rc100.printIndexes();

        RC4 rc1000 = new RC4(keyByteArray);
        System.out.println("Generating first 1000 bytes");
        rc1000.getKeystream(1000);
        System.out.println("Printing S after 1000 bytes");
        rc1000.printSPermutations();
        System.out.println("Printing indexes after 1000 bytes");
        rc1000.printIndexes();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
