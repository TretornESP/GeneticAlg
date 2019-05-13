/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic;

import java.util.Random;

/**
 *
 * @author xabie
 */
public class Result {
    private static int RESULT[] = {0,1,0,1,1,0,1,0,0,1,0,0,0,1,0,0,1,0,1,1,0,1,0,0,1,1,0,1,0,1,0,1};
    
    public static boolean isCorrect(int number[]) {
        if (number.length == RESULT.length) {
            for (int i = 0; i < number.length; i++) {
                if (RESULT[i] != number[i]) return false;
            }
        } else {
            return false;
        }
        return true;
    }
    
    public static void newResult(int size) {
        RESULT = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            RESULT[i] = r.nextInt(2);
        }
    }
    
    public static int getSize() {
        return RESULT.length;
    }
    
    public static int[] getBits() {
        return RESULT;
    }
    
    public static void print() {
        System.out.print("   C:");
        for (Integer i: RESULT) {
            System.out.print(i);
        }
        System.out.println();
    }
}
