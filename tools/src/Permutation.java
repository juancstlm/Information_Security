public class Permutation {

    public static String weakPermutations(String msg, int[] permutation){
        StringBuilder s = new StringBuilder();
        String[] words = msg.split("\\s+");
        for(int i = 0; i<words.length; i++){
            s.append(words[permutation[i]-1]+ " ");
        }
        return s.toString();
    }
}
