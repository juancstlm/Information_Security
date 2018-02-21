import java.util.*;

public class SimpleSubstitutionSolver {

    final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final static String ENGLISH_FREQUENCY = "ETAOINSHRDLCUMWFGYPBVKJXQZ";
    static String comptedFreq = "";


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the ciphertext to begin: ");

        String ciphertext = in.nextLine();
        System.out.println("Starting Letter Frequency Analysis");
        charFreqAnalysis(ciphertext);
        System.out.println("Letter Frequency Analysis Done");

        System.out.println(comptedFreq);
        String pKey = charFreqKey(comptedFreq);
        System.out.println("Attempting to decrypt using letter frequency analysis using the following key: " + pKey);
        System.out.println(decrypt(ciphertext, pKey));

        System.out.println("Enter a new key to decrypt: ");
        while (in.hasNext()) {
            String freq = in.nextLine();
            pKey = charFreqKey(freq);
            System.out.println(decrypt(ciphertext, pKey));
            System.out.println(ciphertext);
            System.out.println("Enter a new freq to decrypt: ");
        }


    }

    private static String decrypt(String text, String key) {
        // Substitutions
        char[] plaintext = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            int c = text.charAt(i);
            int pos = key.indexOf(c);
            plaintext[i] = ALPHABET.charAt(pos);
        }
        return String.valueOf(plaintext);
    }

    static String charFreqKey(String mostFrequentChars) {
        String key = "";
        for (int i = 0; i < ALPHABET.length(); i++) {
            char c = ALPHABET.charAt(i);
            int pos = ENGLISH_FREQUENCY.indexOf(c);
            key += mostFrequentChars.charAt(pos);
        }
        return key;
    }

    private static void charFreqAnalysis(String string) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < ALPHABET.length(); i++) {
            map.put(ALPHABET.charAt(i), 0);
        }

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            Integer val = map.get(c);
            if (val != null) {
                map.put(c, new Integer(val + 1));
            } else {
                map.put(c, 1);
            }
        }

        List<Map.Entry<Character, Integer>> sorted = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            sorted.add(entry);
        }
        sorted.sort(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if (o1.getValue() < o2.getValue()) {
                    return 1;
                } else if (o1.getValue() > o2.getValue()) {
                    return -1;
                } else return 0;
            }
        });
        for (int i = 0; i < sorted.size(); i++) {
            comptedFreq += sorted.get(i).getKey();
        }
        System.out.println(sorted);
    }
}
