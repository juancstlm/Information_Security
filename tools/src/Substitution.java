public class Substitution {

    public static String rotation(String msg, int shift){
        StringBuilder s = new StringBuilder();
        int len = msg.length();
        for(int x = 0; x < len; x++){
            char c = (char)(msg.charAt(x) + shift);
            if (c > 'Z')
                s.append(Character.toChars(msg.charAt(x) - (26-shift)));
            else
                s.append(Character.toChars(msg.charAt(x) + shift));
        }
        return s.toString();
    }


}
