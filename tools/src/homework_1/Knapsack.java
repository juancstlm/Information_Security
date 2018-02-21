package homework_1;

import java.nio.ByteBuffer;
import java.util.Random;

public class Knapsack {
    private int[] superKnapsack;
    private int[] knapsack;
    private int bits;
    private int m; //multiplier
    private int n; //modulo

    public Knapsack(int bits, int m, int n){
        this.bits = bits;
        this.m = m;
        this.n = n;
        superKnapsack = new int[bits];
        knapsack = new  int[bits];
        createSuperIncKnapsac();
        createGeneralKnapsack();
    }

    public  int encrypt(int s){
        String string = Integer.toBinaryString(s);
        char[] chars = string.toCharArray();
        int e = 0;
        for(int i = 0; i < chars.length;i++){
            if(chars[i]=='1'){
                e+= knapsack[i];
            }
        }
        return e;
    }

    public int modulo (int i, int j)  {  return i - (i/j)*j;  }

    public int inverse() {
        int f;
        int a = m;
        int b = n;
        for (int k=1 ; k < b ; k++) {
            f = 0;
            for (int j=0 ; j < b ; j++)
                if (modulo(k*modulo(a*j,b), b) == j) f += 1; else break;
            if (f == b) return k;
        }
        return 0;
    }

    public int decrypt(int ciphertext){

        int inverse = inverse();
        int sum =  modulo(ciphertext *inverse,n);
        byte[] text = new byte[bits];
        for(int i = knapsack.length-1; i >=0 ; i--){
            if(sum>=superKnapsack[i]){
                sum -= superKnapsack[i];
                text[i] = 1;
            } else{
               text[i] = 0;
            }
        }
        int returned = 0;
        for(int i = 0; i < text.length; i++){
            if(text[i] == 1){
                returned += Math.pow(2,text.length-i-1);
            }
        }
        return returned;
    }

    public int[] getPublicKey(){
        return knapsack;
    }

    private void createSuperIncKnapsac(){
        superKnapsack[0] = randInt(0,5);
        int sum = superKnapsack[0];
        for(int i = 1; i < bits ; i++){
            superKnapsack[i] = (sum + randInt(1,5));
            sum += superKnapsack[i];
        }
    }

    private void createGeneralKnapsack(){
        for(int i = 0; i < bits ; i++){
            knapsack[i] = (superKnapsack[i] * m) % n;
        }
    }

    /**
     * Return a random integer in the specified range
     * @param min
     * @param max
     * @return
     */
    private static int randInt(int min, int max){
        Random rando = new Random(2312);
        int randomNum = rando.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
