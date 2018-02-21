import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Tester {

    public static void main(String[] args) {
        //simpleSub();
        affineCipherTest();
    }

    static void charFreqAnalysis(String string) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        String s = string;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer val = map.get(c);
            if (val != null) {
                map.put(c, new Integer(val + 1));
            } else {
                map.put(c, 1);
            }
        }
        System.out.println(map);
    }


    public static void subTester() {
        String ciphertext = "CSYEVIXIVQMREXIH";

        for (int i = 0; i < 26; i++) {
            System.out.println("Plaintext: " + Substitution.rotation(ciphertext, i) + " Key: " + i);
        }
    }

    public static void weakPermTester() {
        String ciphertext1 = "first try try if you and don't again at succeed";
        String ciphertext2 = "only you you you as believe old are are as";
        String ciphertext3 = "winter was in the I summer ever SanFrancisco coldest spent";
        int[] key = {4, 9, 1, 5, 7, 10, 2, 6, 3, 8};


        System.out.println(Permutation.weakPermutations(ciphertext1, key));
        System.out.println(Permutation.weakPermutations(ciphertext2, key));
        System.out.println(Permutation.weakPermutations(ciphertext3, key));

    }
    static void affineCipherTest(){
        String ciphertext ="QJKESREOGHGXXREOXEO";
        int[] text = new int[ciphertext.length()];
        for(int i = 0; i < ciphertext.length(); i++){

            text[i] = ciphertext.charAt(i) - 65;
            System.out.println(text[i]);
        }

        for(int a = 0; a < 26; a++){
            for(int b = 0; b < 26; b++){
                StringBuilder plaintext = new StringBuilder();
                for(int i : text){
                    plaintext.append((char)(affineCipher(a,b,i) + 65));
                }
                if(plaintext.toString().charAt(9) == 'T'){
                    System.out.println(plaintext.toString() + " A = " + a + " B= " + b);
                }
            }
        }

    }
    static int affineCipher( int a, int b, int p){
        return (((a*p) + b )%26);
    }

    static void simpleSub() {
        String ciphertext = "PBFPVYFBQXZTYFPBFEQJHDXXQVAPTPQJKTOYQWIPBVWLXTOXBTFXQWAXBVCXQWAXFQJVWLEQNTOZQGGQLFXQWAKVWLXQWAEBIPBFXFQVXGTVJVWLBTPQWAEBFPBFHCVLXBQUFEVWLXGDPEQVPQGVPPBFTIXPFHXZHVFAGFOTHFEFBQUFTDHZBQPOTHXTYFTODXQHFTDPTOGHFQPBQWAQJJTODXQHFOQPWTBDHHIXQVAPBFZQHCFWPFHPBFIPBQWKFABVYYDZBOTHPBQPQJTQOTOGHFQAPBFEQJHDXXQVAVXEBQPEFZBVFOJIWFFACFCCFHQWAUVWFLQHGFXVAFXQHFUFHILTTAVWAFFAWTEVOITDHFHFQAITIXPFHXAFQHEFZQWGFLVWPTOFFA";

        ciphertext = ciphertext.toUpperCase();

        char[] plaintext = new char[ciphertext.length()];

        // Substitutions
        final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String key = "QGZAFOLBVMKJYWTCRHXPDUENIS";
        for (int i = 0; i < ciphertext.length(); i++) {
            // get the ith character of the ciphertext
            char c = ciphertext.charAt(i);
            // find the position of character in the key;
            int pos = key.indexOf(c);
            plaintext[i] = ALPHABET.charAt(pos);
        }

        System.out.println(plaintext);
        Scanner ss = new Scanner(System.in);
        System.out.println("Enter command");


        while (ss.hasNext()) {

            String command = ss.nextLine();
            String[] sub = command.split("\\s+");
            int old = key.indexOf(sub[0]);
            int newC = key.indexOf(sub[1]);
            char[] tKey = key.toCharArray();
            tKey[old] = sub[1].charAt(0);
            tKey[newC] = sub[0].charAt(0);
            key = String.copyValueOf(tKey);
            System.out.println("Substituting " + sub[0] + " with " + sub[1]);
            System.out.println("New key: " + key);
            //System.out.println("Substituting " + ALPHABET.charAt(i) + " with " + key.charAt(i));

            for (int i = 0; i < ciphertext.length(); i++) {
                int pos = ALPHABET.indexOf(ciphertext.charAt(i));
                plaintext[i] = key.charAt(pos);
            }

            String pText = String.copyValueOf(plaintext);
            System.out.println(pText);

            System.out.println("substitution succeed enter new substitutuion");
        }

    }
}
