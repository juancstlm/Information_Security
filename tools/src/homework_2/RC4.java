package homework_2;

public class RC4 {

    private int[] s = new int[256];
    private int[] k = new int[256];

    private int i = 0;
    private int j = 0;

    public RC4(byte[] key) {
        for (int i = 0; i < 256; i++) {
            //System.out.println(i);
            s[i] = i;
            k[i] =  key[i % key.length];
        }

        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + s[i] + k[i]) % 256;
            swap(s[i], s[j]);
        }


        i = j = 0;
    }

    private void swap(int a, int b) {
        int temp = s[a];
        s[a] = b;
        s[b] = temp;
    }

    private int keyStream() {
        i = (i + 1) % 256;
        j = (j + s[i]) % 256;
        swap(s[i],s[j]);
        int t = (s[i] + s[j]) % 256;
        int keyStreamByte = s[t];
        return keyStreamByte;
    }

    public String decrypt(String cipherText) {
        return null;
    }

    public String encrypt(String plainText) {
        return null;
    }

    public int[] getKeystream(int numBytes){
        int[] keyStream = new int[numBytes];
        for(int i = 0; i < numBytes; i++){
            keyStream[i] = keyStream();
        }
        return keyStream;
    }

    protected void printSPermutations(){
        int counter = 0;
        System.out.print("S={\n");
        for(int b: s){
            counter++;
            System.out.print( Integer.toHexString(b) + " ");
            if(counter == 16){
                System.out.print("\n");
                counter = 0;
            }
        }
        System.out.println("}");
    }

    protected void printIndexes(){
        System.out.println("Index i= " + i + " Index j= " +  j);
    }
}
