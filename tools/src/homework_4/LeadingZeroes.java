package homework_4;

import java.util.Random;

public class LeadingZeroes {


    public static void main(String[] args){

        int numZeroes = 6;
        Random r = new Random();
        String string = String.valueOf(r.nextLong());
        String hash = MD5(string);

        while(!hasLeadingZeroes(hash, numZeroes)){
            string = String.valueOf(r.nextLong());
            hash = MD5(string);
        }

        print("Found a number that hashes to  " + numZeroes + " Zeroes");
        print("Number: " + string);
        print("The corresponding hash: " + hash);

    }


    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    static void print(String s){
        System.out.println(s);
    }

    static boolean hasLeadingZeroes(String s, int n){
        char[] zeros = new char[n];
        for(int i =0; i < n; i++){
            zeros[i] =  '0';
        }
        String string  = String.valueOf(zeros);
        return s.startsWith(string);
    }
}
